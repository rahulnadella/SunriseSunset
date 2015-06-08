/*
 The MIT License (MIT)

 Copyright (c) <year> <copyright holders>

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */
package com.sunrisesunset.util;

import static com.sunrisesunset.util.MathUtility.convertDegreesToRadians;
import static com.sunrisesunset.util.MathUtility.convertRadiansToDegrees;
import static com.sunrisesunset.util.MathUtility.divideBy;
import static com.sunrisesunset.util.MathUtility.getDayOfYear;
import static com.sunrisesunset.util.MathUtility.getUTCOffset;
import static com.sunrisesunset.util.MathUtility.multiplyBy;
import static com.sunrisesunset.util.MathUtility.setScale;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.TimeZone;

import com.sunrisesunset.api.Twilight;

/**
 * The {@link SunriseSunsetCalculator} class contains several helper methods to
 * compute the values of the sunrise/sunset based on the time zone, latitude,
 * and longitude.
 * 
 * @version 1.0
 */
public class SunriseSunsetCalculator {
	/* Accessor values for calculating the sunrise/sunset */
	protected final BigDecimal latitude;
	protected final BigDecimal longitude;
	protected final TimeZone timeZone;

	/**
	 * Creates a {@code SunriseSunsetUtility} object based on the parameters
	 * 
	 * @param timeZoneIdentifier
	 *            the time zone of the specific latitude and longitude. For
	 *            example, "America/New_York".
	 * @param latitude
	 *            the angular distance {@code BigDecimal} of a place north or
	 *            south of the earth's equator
	 * @param longitude
	 *            the angular distance {@code BigDecimal} of a place east or
	 *            west of the earth's equator
	 */
	public SunriseSunsetCalculator(String timeZoneIdentifier,
			BigDecimal latitude, BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.timeZone = TimeZone.getTimeZone(timeZoneIdentifier);
	}

	/**
	 * Creates a {@code SunriseSunsetUtility} object based on the parameters
	 * 
	 * @param timeZoneIdentifier
	 *            the {@code TimeZone} of the specific latitude and longitude.
	 *            For example, "America/New_York".
	 * @param latitude
	 *            the angular distance {@code BigDecimal} of a place north or
	 *            south of the earth's equator
	 * @param longitude
	 *            the angular distance {@code BigDecimal} of a place east or
	 *            west of the earth's equator
	 */
	public SunriseSunsetCalculator(TimeZone timeZone, BigDecimal latitude,
			BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.timeZone = timeZone;
	}

	/**
	 * Creates a {@code SunriseSunsetUtility} object based on the parameters
	 * 
	 * @param timeZoneIdentifier
	 *            the time zone of the specific latitude and longitude. For
	 *            example, "America/New_York".
	 * @param latitude
	 *            the angular distance {@code Double} of a place north or south
	 *            of the earth's equator
	 * @param longitude
	 *            the angular distance {@code Double} of a place east or west of
	 *            the earth's equator
	 */
	public SunriseSunsetCalculator(String timeZoneIdentifier, Double latitude,
			Double longitude) {
		this.timeZone = TimeZone.getTimeZone(timeZoneIdentifier);
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}

	/**
	 * Creates a {@code SunriseSunsetUtility} object based on the parameters
	 * 
	 * @param timeZoneIdentifier
	 *            the {@code TimeZone} of the specific latitude and longitude.
	 *            For example, "America/New_York".
	 * @param latitude
	 *            the angular distance {@code Double} of a place north or south
	 *            of the earth's equator
	 * @param longitude
	 *            the angular distance {@code Double} of a place east or west of
	 *            the earth's equator
	 */
	public SunriseSunsetCalculator(TimeZone timeZone, Double latitude,
			Double longitude) {
		this.timeZone = timeZone;
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}

	/**
	 * Creates a {@code SunriseSunsetUtility} object based on the parameters
	 * 
	 * @param timeZoneIdentifier
	 *            the time zone of the specific latitude and longitude. For
	 *            example, "America/New_York".
	 * @param latitude
	 *            the angular distance {@code String} of a place north or south
	 *            of the earth's equator
	 * @param longitude
	 *            the angular distance {@code String} of a place east or west of
	 *            the earth's equator
	 */
	public SunriseSunsetCalculator(String timeZoneIdentifier, String latitude,
			String longitude) {
		this.timeZone = TimeZone.getTimeZone(timeZoneIdentifier);
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}

	/**
	 * Creates a {@code SunriseSunsetUtility} object based on the parameters
	 * 
	 * @param timeZoneIdentifier
	 *            the {@code TimeZone} of the specific latitude and longitude.
	 *            For example, "America/New_York".
	 * @param latitude
	 *            the angular distance {@code String} of a place north or south
	 *            of the earth's equator
	 * @param longitude
	 *            the angular distance {@code String} of a place east or west of
	 *            the earth's equator
	 */
	public SunriseSunsetCalculator(TimeZone timeZone, String latitude,
			String longitude) {
		this.timeZone = timeZone;
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}

	/**
	 * Computes the sunrise time for the given {@link Twilight} at the given
	 * date.
	 *
	 * @param twilight
	 *            The {@code Twilight} corresponding to the type of sunrise to
	 *            compute.
	 * @param date
	 *            The {@code Calendar} object representing the date to compute
	 *            the sunrise for.
	 * @return the sunrise time, in HH:MM format (24-hour clock), 00:00 if the
	 *         sun does not rise on the given date.
	 */
	public String computeSunriseTime(Twilight twilight, Calendar date) {
		return getLocalTimeAsString(computeSolarEventTime(twilight, date, true));
	}

	/**
	 * Computes the sunrise time for the given {@link Twilight} at the given
	 * date.
	 *
	 * @param twilight
	 *            The {@code Twilight} corresponding to the type of sunrise to
	 *            compute.
	 * @param date
	 *            The {@code Calendar} object representing the date to compute
	 *            the sunrise for.
	 * @return the sunrise time as a calendar or null for no sunrise
	 */
	public Calendar computeSunriseCalendar(Twilight twilight, Calendar date) {
		return getLocalTimeAsCalendar(
				computeSolarEventTime(twilight, date, true), date);
	}

	/**
	 * Computes the sunset time for the given {@link Twilight} at the given
	 * date.
	 *
	 * @param twilight
	 *            The {@code Twilight} corresponding to the type of sunset to
	 *            compute.
	 * @param date
	 *            The {@code Calendar} object representing the date to compute
	 *            the sunset for.
	 * @return the sunset time, in HH:MM format (24-hour clock), 00:00 if the
	 *         sun does not set on the given date.
	 */
	public String computeSunsetTime(Twilight twilight, Calendar date) {
		return getLocalTimeAsString(computeSolarEventTime(twilight, date, false));
	}

	/**
	 * Computes the sunset time for the given {@link Twilight} at the given
	 * date.
	 *
	 * @param twilight
	 *            The {@code Twilight} corresponding to the type of sunset to
	 *            compute.
	 * @param date
	 *            The {@code Calendar} object representing the date to compute
	 *            the sunset for.
	 * @return the sunset time as a calendar or null for no sunset
	 */
	public Calendar computeSunsetCalendar(Twilight twilight, Calendar date) {
		return getLocalTimeAsCalendar(
				computeSolarEventTime(twilight, date, false), date);
	}

	private BigDecimal computeSolarEventTime(Twilight solarZenith,
			Calendar date, boolean isSunrise) {
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

	private BigDecimal getBaseLongitudeHour() {
		return divideBy(longitude, BigDecimal.valueOf(15));
	}

	private BigDecimal getLongitudeHour(Calendar date, Boolean isSunrise) {
		int offset = 18;
		if (isSunrise) {
			offset = 6;
		}
		BigDecimal dividend = BigDecimal.valueOf(offset).subtract(
				getBaseLongitudeHour());
		BigDecimal addend = divideBy(dividend, BigDecimal.valueOf(24));
		BigDecimal longHour = getDayOfYear(date).add(addend);
		return setScale(longHour);
	}

	private BigDecimal getMeanAnomaly(BigDecimal longitudeHour) {
		BigDecimal meanAnomaly = multiplyBy(new BigDecimal("0.9856"),
				longitudeHour).subtract(new BigDecimal("3.289"));
		return setScale(meanAnomaly);
	}

	private BigDecimal getSunTrueLongitude(BigDecimal meanAnomaly) {
		BigDecimal sinMeanAnomaly = new BigDecimal(Math.sin(MathUtility
				.convertDegreesToRadians(meanAnomaly).doubleValue()));
		BigDecimal sinDoubleMeanAnomaly = new BigDecimal(Math.sin(multiplyBy(
				convertDegreesToRadians(meanAnomaly), BigDecimal.valueOf(2))
				.doubleValue()));

		BigDecimal firstPart = meanAnomaly.add(multiplyBy(sinMeanAnomaly,
				new BigDecimal("1.916")));
		BigDecimal secondPart = multiplyBy(sinDoubleMeanAnomaly,
				new BigDecimal("0.020")).add(new BigDecimal("282.634"));
		BigDecimal trueLongitude = firstPart.add(secondPart);

		if (trueLongitude.doubleValue() > 360) {
			trueLongitude = trueLongitude.subtract(BigDecimal.valueOf(360));
		}
		return setScale(trueLongitude);
	}

	private BigDecimal getRightAscension(BigDecimal sunTrueLong) {
		BigDecimal tanL = new BigDecimal(Math.tan(MathUtility
				.convertDegreesToRadians(sunTrueLong).doubleValue()));

		BigDecimal innerParens = multiplyBy(convertRadiansToDegrees(tanL),
				new BigDecimal("0.91764"));
		BigDecimal rightAscension = new BigDecimal(Math.atan(MathUtility
				.convertDegreesToRadians(innerParens).doubleValue()));
		rightAscension = setScale(MathUtility
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
		return divideBy(rightAscension.add(augend), BigDecimal.valueOf(15));
	}

	private BigDecimal getCosineSunLocalHour(BigDecimal sunTrueLong,
			Twilight zenith) {
		BigDecimal sinSunDeclination = getSinOfSunDeclination(sunTrueLong);
		BigDecimal cosineSunDeclination = getCosineOfSunDeclination(sinSunDeclination);

		BigDecimal zenithInRads = MathUtility.convertDegreesToRadians(zenith
				.degrees());
		BigDecimal cosineZenith = BigDecimal.valueOf(Math.cos(zenithInRads
				.doubleValue()));
		BigDecimal sinLatitude = BigDecimal.valueOf(Math
				.sin(convertDegreesToRadians(latitude).doubleValue()));
		BigDecimal cosLatitude = BigDecimal.valueOf(Math
				.cos(convertDegreesToRadians(latitude).doubleValue()));

		BigDecimal sinDeclinationTimesSinLat = sinSunDeclination
				.multiply(sinLatitude);
		BigDecimal dividend = cosineZenith.subtract(sinDeclinationTimesSinLat);
		BigDecimal divisor = cosineSunDeclination.multiply(cosLatitude);

		return setScale(divideBy(dividend, divisor));
	}

	private BigDecimal getSinOfSunDeclination(BigDecimal sunTrueLong) {
		BigDecimal sinTrueLongitude = BigDecimal.valueOf(Math
				.sin(convertDegreesToRadians(sunTrueLong).doubleValue()));
		BigDecimal sinOfDeclination = sinTrueLongitude.multiply(new BigDecimal(
				"0.39782"));
		return setScale(sinOfDeclination);
	}

	private BigDecimal getCosineOfSunDeclination(BigDecimal sinSunDeclination) {
		BigDecimal arcSinOfSinDeclination = BigDecimal.valueOf(Math
				.asin(sinSunDeclination.doubleValue()));
		BigDecimal cosDeclination = BigDecimal.valueOf(Math
				.cos(arcSinOfSinDeclination.doubleValue()));
		return setScale(cosDeclination);
	}

	private BigDecimal getSunLocalHour(BigDecimal cosineSunLocalHour,
			Boolean isSunrise) {
		BigDecimal arcCosineOfCosineHourAngle = MathUtility
				.getArcCosineFor(cosineSunLocalHour);
		BigDecimal localHour = MathUtility
				.convertRadiansToDegrees(arcCosineOfCosineHourAngle);
		if (isSunrise) {
			localHour = BigDecimal.valueOf(360).subtract(localHour);
		}
		return divideBy(localHour, BigDecimal.valueOf(15));
	}

	private BigDecimal getLocalMeanTime(BigDecimal sunTrueLong,
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

	private BigDecimal getLocalTime(BigDecimal localMeanTime, Calendar date) {
		BigDecimal utcTime = localMeanTime.subtract(getBaseLongitudeHour());
		BigDecimal utcOffSet = getUTCOffset(date);
		BigDecimal utcOffSetTime = utcTime.add(utcOffSet);
		return adjustForDST(utcOffSetTime, date);
	}

	private BigDecimal adjustForDST(BigDecimal localMeanTime, Calendar date) {
		BigDecimal localTime = localMeanTime;
		if (timeZone.inDaylightTime(date.getTime())) {
			localTime = localTime.add(BigDecimal.ONE);
		}
		if (localTime.doubleValue() > 24.0) {
			localTime = localTime.subtract(BigDecimal.valueOf(24));
		}
		return localTime;
	}

	private String getLocalTimeAsString(BigDecimal localTimeParam) {
		if (localTimeParam == null) {
			return "99:99";
		}

		if (localTimeParam.compareTo(BigDecimal.ZERO) == -1) {
			localTimeParam = localTimeParam.add(BigDecimal.valueOf(24.0D));
		}
		String[] timeComponents = localTimeParam.toPlainString().split("\\.");
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

	private Calendar getLocalTimeAsCalendar(BigDecimal localTimeParam,
			Calendar date) {
		if (localTimeParam == null) {
			return null;
		}

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
