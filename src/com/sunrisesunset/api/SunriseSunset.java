package com.sunrisesunset.api;

import static com.sunrisesunset.api.Twilight.ASTRONOMICAL;
import static com.sunrisesunset.api.Twilight.CIVIL;
import static com.sunrisesunset.api.Twilight.NAUTICAL;
import static com.sunrisesunset.api.Twilight.OFFICIAL;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;

import com.sunrisesunset.util.SunriseSunsetUtility;

/**
 * Sunrise or sun up is the instant at which the upper edge of the Sun appears
 * over the eastern horizon in the morning. The term can also refer to the
 * entire process of the Sun crossing the horizon and its accompanying
 * atmospheric effects.
 * 
 * Sunset is the time of sunset is defined in astronomy as the moment when the
 * trailing edge of the Sun's disk disappears below the horizon. The ray path of
 * light from the setting Sun is highly distorted near the horizon because of
 * atmospheric refraction, making the sunset appear to occur when the Sun’s disk
 * is already about one diameter below the horizon. Sunset is distinct from
 * dusk, which is the time at which the sky becomes completely dark, which
 * occurs when the Sun is approximately eighteen degrees below the horizon. The
 * period between sunset and dusk is called twilight.
 * 
 * The {@link SunriseSunset} class provides the ability to retrieve the
 * sunrise/sunset and 3 types of {@code Twilight} based on specific timezone,
 * latitude and longitude.
 * 
 * @version 1.0
 */
public class SunriseSunset {
	/* The values associated to the specific Location */
	protected BigDecimal latitude;
	protected BigDecimal longitude;
	/* The value to set to calculate the sunrise/sunset */
	protected SunriseSunsetUtility sunrisesunset;

	/**
	 * The {@link SunriseSunset} initialization based on time zone
	 * {@code String}, latitude {@code BigDecimal}, and longitude
	 * {@code BigDecimal}
	 * 
	 * @param timeZoneIdentifier
	 *            A {@code String} based on the time zone is a region that
	 *            observes a uniform standard time for legal, commercial, and
	 *            social purposes.
	 * @param latitude
	 *            A {@code BigDecimal} value that represents latitude of the
	 *            specific location
	 * @param longitude
	 *            A {@code BigDecimal} value that represents longitude of the
	 *            specific location
	 */
	public SunriseSunset(String timeZoneIdentifier, BigDecimal latitude,
			BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.sunrisesunset = new SunriseSunsetUtility(timeZoneIdentifier,
				latitude, longitude);
	}

	/**
	 * The {@link SunriseSunset} initialization based on time zone
	 * {@code TimeZone}, latitude {@code BigDecimal}, and longitude
	 * {@code BigDecimal}
	 * 
	 * @param timeZoneIdentifier
	 *            A {@code TimeZone} based on the time zone is a region that
	 *            observes a uniform standard time for legal, commercial, and
	 *            social purposes.
	 * @param latitude
	 *            A {@code BigDecimal} value that represents latitude of the
	 *            specific location
	 * @param longitude
	 *            A {@code BigDecimal} value that represents longitude of the
	 *            specific location
	 */
	public SunriseSunset(TimeZone timeZone, BigDecimal latitude,
			BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.sunrisesunset = new SunriseSunsetUtility(timeZone, latitude,
				longitude);
	}

	/**
	 * The {@link SunriseSunset} initialization based on time zone
	 * {@code String}, latitude {@code Double}, and longitude {@code Double}
	 * 
	 * @param timeZoneIdentifier
	 *            A {@code String} based on the time zone is a region that
	 *            observes a uniform standard time for legal, commercial, and
	 *            social purposes.
	 * @param latitude
	 *            A {@code Double} value that represents latitude of the
	 *            specific location
	 * @param longitude
	 *            A {@code Double} value that represents longitude of the
	 *            specific location
	 */
	public SunriseSunset(String timeZoneIdentifier, Double latitude,
			Double longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
		this.sunrisesunset = new SunriseSunsetUtility(timeZoneIdentifier,
				latitude, longitude);
	}

	/**
	 * The {@link SunriseSunset} initialization based on time zone
	 * {@code TimeZone}, latitude {@code Double}, and longitude {@code Double}
	 * 
	 * @param timeZoneIdentifier
	 *            A {@code TimeZone} based on the time zone is a region that
	 *            observes a uniform standard time for legal, commercial, and
	 *            social purposes.
	 * @param latitude
	 *            A {@code Double} value that represents latitude of the
	 *            specific location
	 * @param longitude
	 *            A {@code Double} value that represents longitude of the
	 *            specific location
	 */
	public SunriseSunset(TimeZone timeZone, Double latitude, Double longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
		this.sunrisesunset = new SunriseSunsetUtility(timeZone, latitude,
				longitude);
	}

	/**
	 * The {@link SunriseSunset} initialization based on time zone
	 * {@code String}, latitude {@code String}, and longitude {@code String}
	 * 
	 * @param timeZoneIdentifier
	 *            A {@code String} based on the time zone is a region that
	 *            observes a uniform standard time for legal, commercial, and
	 *            social purposes.
	 * @param latitude
	 *            A {@code String} value that represents latitude of the
	 *            specific location
	 * @param longitude
	 *            A {@code String} value that represents longitude of the
	 *            specific location
	 */
	public SunriseSunset(String timeZoneIdentifier, String latitude,
			String longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
		this.sunrisesunset = new SunriseSunsetUtility(timeZoneIdentifier,
				latitude, longitude);
	}

	/**
	 * The {@link SunriseSunset} initialization based on time zone
	 * {@code TimeZone}, latitude {@code String}, and longitude {@code String}
	 * 
	 * @param timeZoneIdentifier
	 *            A {@code TimeZone} based on the time zone is a region that
	 *            observes a uniform standard time for legal, commercial, and
	 *            social purposes.
	 * @param latitude
	 *            A {@code String} value that represents latitude of the
	 *            specific location
	 * @param longitude
	 *            A {@code String} value that represents longitude of the
	 *            specific location
	 */
	public SunriseSunset(TimeZone timeZone, String latitude, String longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
		this.sunrisesunset = new SunriseSunsetUtility(timeZone, latitude,
				longitude);
	}

	/**
	 * Returns the astronomical twilight associated to sunrise which occurs when
	 * the Sun is between 12 degrees and 18 degrees below the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            astronomical sunrise for.
	 * @return The astronomical sunrise time in HH:MM (24-hour clock) form.
	 */
	public String getAstronomicalSunrise(Calendar date) {
		return sunrisesunset.computeSunriseTime(ASTRONOMICAL, date);
	}

	/**
	 * Returns the astronomical twilight associated to sunrise which occurs when
	 * the Sun is between 12 degrees and 18 degrees below the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            astronomical sunrise for.
	 * @return The astronomical sunrise time as a {@code Calendar}
	 */
	public Calendar getAstronomicalSunriseCalendar(Calendar date) {
		return sunrisesunset.computeSunriseCalendar(ASTRONOMICAL, date);
	}

	/**
	 * Returns the astronomical twilight associated to sunset which occurs when
	 * the Sun is between 12 degrees and 18 degrees below the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            astronomical sunrise for.
	 * @return The astronomical sunset time in HH:MM (24-hour clock) form.
	 */
	public String getAstronomicalSunset(Calendar date) {
		return sunrisesunset.computeSunsetTime(ASTRONOMICAL, date);
	}

	/**
	 * Returns the astronomical twilight associated to sunset which occurs when
	 * the Sun is between 12 degrees and 18 degrees below the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            astronomical sunrise for.
	 * @return The astronomical sunset time as a {@code Calendar}
	 */
	public Calendar getAstronomicalSunsetCalendar(Calendar date) {
		return sunrisesunset.computeSunsetCalendar(ASTRONOMICAL, date);
	}

	public String getNauticalSunrise(Calendar date) {
		return sunrisesunset.computeSunriseTime(NAUTICAL, date);
	}

	public Calendar getNauticalSunriseCalendar(Calendar date) {
		return sunrisesunset.computeSunriseCalendar(NAUTICAL, date);
	}

	public String getNauticalSunset(Calendar date) {
		return sunrisesunset.computeSunsetTime(NAUTICAL, date);
	}

	public Calendar getNauticalSunsetCalendar(Calendar date) {
		return sunrisesunset.computeSunsetCalendar(NAUTICAL, date);
	}

	public String getCivilSunrise(Calendar date) {
		return sunrisesunset.computeSunriseTime(CIVIL, date);
	}

	public Calendar getCivilSunriseCalendar(Calendar date) {
		return sunrisesunset.computeSunriseCalendar(CIVIL, date);
	}

	public String getCivilSunset(Calendar date) {
		return sunrisesunset.computeSunsetTime(CIVIL, date);
	}

	public Calendar getCivilSunsetCalendar(Calendar date) {
		return sunrisesunset.computeSunsetCalendar(CIVIL, date);
	}

	public String getOfficialSunrise(Calendar date) {
		return sunrisesunset.computeSunriseTime(OFFICIAL, date);
	}

	public Calendar getOfficialSunriseCalendar(Calendar date) {
		return sunrisesunset.computeSunriseCalendar(OFFICIAL, date);
	}

	public String getOfficialSunset(Calendar date) {
		return sunrisesunset.computeSunsetTime(OFFICIAL, date);
	}

	public Calendar getOfficialSunsetCalendar(Calendar date) {
		return sunrisesunset.computeSunsetCalendar(OFFICIAL, date);
	}

	public static Calendar getSunrise(double latitude, double longitude,
			TimeZone timeZone, Calendar date, double degrees) {
		return new SunriseSunsetUtility(timeZone, new BigDecimal(latitude),
				new BigDecimal(longitude)).computeSunriseCalendar(new Twilight(
				90 - degrees), date);
	}

	public static Calendar getSunset(double latitude, double longitude,
			TimeZone timeZone, Calendar date, double degrees) {
		return new SunriseSunsetUtility(timeZone, new BigDecimal(latitude),
				new BigDecimal(longitude)).computeSunsetCalendar(new Twilight(
				90 - degrees), date);
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}
}
