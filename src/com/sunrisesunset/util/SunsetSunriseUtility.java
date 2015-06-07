package com.sunrisesunset.util;

import static com.sunrisesunset.util.TimeUtility.convertDegreesToRadians;
import static com.sunrisesunset.util.TimeUtility.convertRadiansToDegrees;
import static com.sunrisesunset.util.TimeUtility.divideBy;
import static com.sunrisesunset.util.TimeUtility.getDayOfYear;
import static com.sunrisesunset.util.TimeUtility.getUTCOffset;
import static com.sunrisesunset.util.TimeUtility.multiplyBy;
import static com.sunrisesunset.util.TimeUtility.setScale;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.TimeZone;

import com.sunrisesunset.api.Location;
import com.sunrisesunset.api.Twilight;

public class SunsetSunriseUtility {

	protected final Location location;
	protected final TimeZone timeZone;

	public SunsetSunriseUtility(Location location, String timeZoneIdentifier) {
		this.location = location;
		this.timeZone = TimeZone.getTimeZone(timeZoneIdentifier);
	}

	public SunsetSunriseUtility(Location location, TimeZone timeZone) {
		this.location = location;
		this.timeZone = timeZone;
	}

	/**
	 * Computes the sunrise time for the given zenith at the given date.
	 *
	 * @param solarZenith
	 *            <code>Zenith</code> enum corresponding to the type of sunrise
	 *            to compute.
	 * @param date
	 *            <code>Calendar</code> object representing the date to compute
	 *            the sunrise for.
	 * @return the sunrise time, in HH:MM format (24-hour clock), 00:00 if the
	 *         sun does not rise on the given date.
	 */
	public String computeSunriseTime(Twilight solarZenith, Calendar date) {
		return getLocalTimeAsString(computeSolarEventTime(solarZenith, date,
				true));
	}

	/**
	 * Computes the sunrise time for the given zenith at the given date.
	 *
	 * @param solarZenith
	 *            <code>Zenith</code> enum corresponding to the type of sunrise
	 *            to compute.
	 * @param date
	 *            <code>Calendar</code> object representing the date to compute
	 *            the sunrise for.
	 * @return the sunrise time as a calendar or null for no sunrise
	 */
	public Calendar computeSunriseCalendar(Twilight solarZenith, Calendar date) {
		return getLocalTimeAsCalendar(
				computeSolarEventTime(solarZenith, date, true), date);
	}

	/**
	 * Computes the sunset time for the given zenith at the given date.
	 *
	 * @param solarZenith
	 *            <code>Zenith</code> enum corresponding to the type of sunset
	 *            to compute.
	 * @param date
	 *            <code>Calendar</code> object representing the date to compute
	 *            the sunset for.
	 * @return the sunset time, in HH:MM format (24-hour clock), 00:00 if the
	 *         sun does not set on the given date.
	 */
	public String computeSunsetTime(Twilight solarZenith, Calendar date) {
		return getLocalTimeAsString(computeSolarEventTime(solarZenith, date,
				false));
	}

	/**
	 * Computes the sunset time for the given zenith at the given date.
	 *
	 * @param solarZenith
	 *            <code>Zenith</code> enum corresponding to the type of sunset
	 *            to compute.
	 * @param date
	 *            <code>Calendar</code> object representing the date to compute
	 *            the sunset for.
	 * @return the sunset time as a Calendar or null for no sunset.
	 */
	public Calendar computeSunsetCalendar(Twilight solarZenith, Calendar date) {
		return getLocalTimeAsCalendar(
				computeSolarEventTime(solarZenith, date, false), date);
	}

	protected BigDecimal computeSolarEventTime(Twilight solarZenith, Calendar date,
			boolean isSunrise) {
		date.setTimeZone(this.timeZone);
		BigDecimal longitudeHour = getLongitudeHour(date, isSunrise);

		BigDecimal meanAnomaly = getMeanAnomaly(longitudeHour);
		BigDecimal sunTrueLong = getSunTrueLongitude(meanAnomaly);
		BigDecimal cosineSunLocalHour = getCosineSunLocalHour(sunTrueLong,
				solarZenith);
		if ((cosineSunLocalHour.doubleValue() < -1.0)
				|| (cosineSunLocalHour.doubleValue() > 1.0)) {
			return null;
		}

		BigDecimal sunLocalHour = getSunLocalHour(cosineSunLocalHour, isSunrise);
		BigDecimal localMeanTime = getLocalMeanTime(sunTrueLong, longitudeHour,
				sunLocalHour);
		BigDecimal localTime = getLocalTime(localMeanTime, date);
		return localTime;
	}

	/**
	 * Computes the base longitude hour, lngHour in the algorithm.
	 *
	 * @return the longitude of the location of the solar event divided by 15
	 *         (deg/hour), in <code>BigDecimal</code> form.
	 */
	protected BigDecimal getBaseLongitudeHour() {
		return divideBy(location.getLongitude(),
				BigDecimal.valueOf(15));
	}

	/**
	 * Computes the longitude time, t in the algorithm.
	 *
	 * @return longitudinal time in <code>BigDecimal</code> form.
	 */
	protected BigDecimal getLongitudeHour(Calendar date, Boolean isSunrise) {
		int offset = 18;
		if (isSunrise) {
			offset = 6;
		}
		BigDecimal dividend = BigDecimal.valueOf(offset).subtract(
				getBaseLongitudeHour());
		BigDecimal addend = divideBy(dividend,
				BigDecimal.valueOf(24));
		BigDecimal longHour = getDayOfYear(date).add(addend);
		return setScale(longHour);
	}

	/**
	 * Computes the mean anomaly of the Sun, M in the algorithm.
	 *
	 * @return the suns mean anomaly, M, in <code>BigDecimal</code> form.
	 */
	protected BigDecimal getMeanAnomaly(BigDecimal longitudeHour) {
		BigDecimal meanAnomaly = multiplyBy(
				new BigDecimal("0.9856"), longitudeHour).subtract(
				new BigDecimal("3.289"));
		return setScale(meanAnomaly);
	}

	/**
	 * Computes the true longitude of the sun, L in the algorithm, at the given
	 * location, adjusted to fit in the range [0-360].
	 *
	 * @param meanAnomaly
	 *            the suns mean anomaly.
	 * @return the suns true longitude, in <code>BigDecimal</code> form.
	 */
	protected BigDecimal getSunTrueLongitude(BigDecimal meanAnomaly) {
		BigDecimal sinMeanAnomaly = new BigDecimal(Math.sin(TimeUtility
				.convertDegreesToRadians(meanAnomaly).doubleValue()));
		BigDecimal sinDoubleMeanAnomaly = new BigDecimal(
				Math.sin(multiplyBy(
						convertDegreesToRadians(meanAnomaly),
						BigDecimal.valueOf(2)).doubleValue()));

		BigDecimal firstPart = meanAnomaly.add(multiplyBy(
				sinMeanAnomaly, new BigDecimal("1.916")));
		BigDecimal secondPart = multiplyBy(
				sinDoubleMeanAnomaly, new BigDecimal("0.020")).add(
				new BigDecimal("282.634"));
		BigDecimal trueLongitude = firstPart.add(secondPart);

		if (trueLongitude.doubleValue() > 360) {
			trueLongitude = trueLongitude.subtract(BigDecimal.valueOf(360));
		}
		return setScale(trueLongitude);
	}

	/**
	 * Computes the suns right ascension, RA in the algorithm, adjusting for the
	 * quadrant of L and turning it into degree-hours. Will be in the range
	 * [0,360].
	 *
	 * @param sunTrueLong
	 *            Suns true longitude, in <code>BigDecimal</code>
	 * @return suns right ascension in degree-hours, in <code>BigDecimal</code>
	 *         form.
	 */
	protected BigDecimal getRightAscension(BigDecimal sunTrueLong) {
		BigDecimal tanL = new BigDecimal(Math.tan(TimeUtility
				.convertDegreesToRadians(sunTrueLong).doubleValue()));

		BigDecimal innerParens = multiplyBy(
				convertRadiansToDegrees(tanL),
				new BigDecimal("0.91764"));
		BigDecimal rightAscension = new BigDecimal(Math.atan(TimeUtility
				.convertDegreesToRadians(innerParens).doubleValue()));
		rightAscension = setScale(TimeUtility
				.convertRadiansToDegrees(rightAscension));

		if (rightAscension.doubleValue() < 0) {
			rightAscension = rightAscension.add(BigDecimal.valueOf(360));
		} else if (rightAscension.doubleValue() > 360) {
			rightAscension = rightAscension.subtract(BigDecimal.valueOf(360));
		}

		BigDecimal ninety = BigDecimal.valueOf(90);
		BigDecimal longitudeQuadrant = sunTrueLong.divide(ninety, 0,
				RoundingMode.FLOOR);
		longitudeQuadrant = longitudeQuadrant.multiply(ninety);

		BigDecimal rightAscensionQuadrant = rightAscension.divide(ninety, 0,
				RoundingMode.FLOOR);
		rightAscensionQuadrant = rightAscensionQuadrant.multiply(ninety);

		BigDecimal augend = longitudeQuadrant.subtract(rightAscensionQuadrant);
		return divideBy(rightAscension.add(augend),
				BigDecimal.valueOf(15));
	}

	protected BigDecimal getCosineSunLocalHour(BigDecimal sunTrueLong,
			Twilight zenith) {
		BigDecimal sinSunDeclination = getSinOfSunDeclination(sunTrueLong);
		BigDecimal cosineSunDeclination = getCosineOfSunDeclination(sinSunDeclination);

		BigDecimal zenithInRads = TimeUtility
				.convertDegreesToRadians(zenith.degrees());
		BigDecimal cosineZenith = BigDecimal.valueOf(Math.cos(zenithInRads
				.doubleValue()));
		BigDecimal sinLatitude = BigDecimal
				.valueOf(Math.sin(convertDegreesToRadians(
						location.getLatitude()).doubleValue()));
		BigDecimal cosLatitude = BigDecimal
				.valueOf(Math.cos(convertDegreesToRadians(
						location.getLatitude()).doubleValue()));

		BigDecimal sinDeclinationTimesSinLat = sinSunDeclination
				.multiply(sinLatitude);
		BigDecimal dividend = cosineZenith.subtract(sinDeclinationTimesSinLat);
		BigDecimal divisor = cosineSunDeclination.multiply(cosLatitude);

		return setScale(divideBy(dividend,
				divisor));
	}

	protected BigDecimal getSinOfSunDeclination(BigDecimal sunTrueLong) {
		BigDecimal sinTrueLongitude = BigDecimal.valueOf(Math
				.sin(convertDegreesToRadians(sunTrueLong)
						.doubleValue()));
		BigDecimal sinOfDeclination = sinTrueLongitude.multiply(new BigDecimal(
				"0.39782"));
		return setScale(sinOfDeclination);
	}

	protected BigDecimal getCosineOfSunDeclination(BigDecimal sinSunDeclination) {
		BigDecimal arcSinOfSinDeclination = BigDecimal.valueOf(Math
				.asin(sinSunDeclination.doubleValue()));
		BigDecimal cosDeclination = BigDecimal.valueOf(Math
				.cos(arcSinOfSinDeclination.doubleValue()));
		return setScale(cosDeclination);
	}

	protected BigDecimal getSunLocalHour(BigDecimal cosineSunLocalHour,
			Boolean isSunrise) {
		BigDecimal arcCosineOfCosineHourAngle = TimeUtility
				.getArcCosineFor(cosineSunLocalHour);
		BigDecimal localHour = TimeUtility
				.convertRadiansToDegrees(arcCosineOfCosineHourAngle);
		if (isSunrise) {
			localHour = BigDecimal.valueOf(360).subtract(localHour);
		}
		return divideBy(localHour, BigDecimal.valueOf(15));
	}

	protected BigDecimal getLocalMeanTime(BigDecimal sunTrueLong,
			BigDecimal longitudeHour, BigDecimal sunLocalHour) {
		BigDecimal rightAscension = this.getRightAscension(sunTrueLong);
		BigDecimal innerParens = longitudeHour.multiply(new BigDecimal(
				"0.06571"));
		BigDecimal localMeanTime = sunLocalHour.add(rightAscension).subtract(
				innerParens);
		localMeanTime = localMeanTime.subtract(new BigDecimal("6.622"));

		if (localMeanTime.doubleValue() < 0) {
			localMeanTime = localMeanTime.add(BigDecimal.valueOf(24));
		} else if (localMeanTime.doubleValue() > 24) {
			localMeanTime = localMeanTime.subtract(BigDecimal.valueOf(24));
		}
		return setScale(localMeanTime);
	}

	protected BigDecimal getLocalTime(BigDecimal localMeanTime, Calendar date) {
		BigDecimal utcTime = localMeanTime.subtract(getBaseLongitudeHour());
		BigDecimal utcOffSet = getUTCOffset(date);
		BigDecimal utcOffSetTime = utcTime.add(utcOffSet);
		return adjustForDST(utcOffSetTime, date);
	}

	protected BigDecimal adjustForDST(BigDecimal localMeanTime, Calendar date) {
		BigDecimal localTime = localMeanTime;
		if (timeZone.inDaylightTime(date.getTime())) {
			localTime = localTime.add(BigDecimal.ONE);
		}
		if (localTime.doubleValue() > 24.0) {
			localTime = localTime.subtract(BigDecimal.valueOf(24));
		}
		return localTime;
	}

	protected String getLocalTimeAsString(BigDecimal localTimeParam) {
		if (localTimeParam == null) {
			return "99:99";
		}

		BigDecimal localTime = localTimeParam;
		if (localTime.compareTo(BigDecimal.ZERO) == -1) {
			localTime = localTime.add(BigDecimal.valueOf(24.0D));
		}
		String[] timeComponents = localTime.toPlainString().split("\\.");
		int hour = Integer.parseInt(timeComponents[0]);

		BigDecimal minutes = new BigDecimal("0." + timeComponents[1]);
		minutes = minutes.multiply(BigDecimal.valueOf(60)).setScale(0,
				RoundingMode.HALF_EVEN);
		if (minutes.intValue() == 60) {
			minutes = BigDecimal.ZERO;
			hour += 1;
		}
		if (hour == 24) {
			hour = 0;
		}

		String minuteString = minutes.intValue() < 10 ? "0"
				+ minutes.toPlainString() : minutes.toPlainString();
		String hourString = (hour < 10) ? "0" + String.valueOf(hour) : String
				.valueOf(hour);
		return hourString + ":" + minuteString;
	}

	protected Calendar getLocalTimeAsCalendar(BigDecimal localTimeParam,
			Calendar date) {
		if (localTimeParam == null) {
			return null;
		}

		// Create a clone of the input calendar so we get locale/timezone
		// information.
		Calendar resultTime = (Calendar) date.clone();

		BigDecimal localTime = localTimeParam;
		if (localTime.compareTo(BigDecimal.ZERO) == -1) {
			localTime = localTime.add(BigDecimal.valueOf(24.0D));
			resultTime.add(Calendar.HOUR_OF_DAY, -24);
		}
		String[] timeComponents = localTime.toPlainString().split("\\.");
		int hour = Integer.parseInt(timeComponents[0]);

		BigDecimal minutes = new BigDecimal("0." + timeComponents[1]);
		minutes = minutes.multiply(BigDecimal.valueOf(60)).setScale(0,
				RoundingMode.HALF_EVEN);
		if (minutes.intValue() == 60) {
			minutes = BigDecimal.ZERO;
			hour += 1;
		}
		if (hour == 24) {
			hour = 0;
		}

		// Set the local time
		resultTime.set(Calendar.HOUR_OF_DAY, hour);
		resultTime.set(Calendar.MINUTE, minutes.intValue());
		resultTime.set(Calendar.SECOND, 0);
		resultTime.set(Calendar.MILLISECOND, 0);
		resultTime.setTimeZone(date.getTimeZone());

		return resultTime;
	}
}
