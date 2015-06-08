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
package main.java;

import static main.java.Twilight.ASTRONOMICAL;
import static main.java.Twilight.CIVIL;
import static main.java.Twilight.NAUTICAL;
import static main.java.Twilight.OFFICIAL;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;

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
	protected SunriseSunsetCalculator sunrisesunset;

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
		this.sunrisesunset = new SunriseSunsetCalculator(timeZoneIdentifier,
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
		this.sunrisesunset = new SunriseSunsetCalculator(timeZone, latitude,
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
		this.sunrisesunset = new SunriseSunsetCalculator(timeZoneIdentifier,
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
		this.sunrisesunset = new SunriseSunsetCalculator(timeZone, latitude,
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
		this.sunrisesunset = new SunriseSunsetCalculator(timeZoneIdentifier,
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
		this.sunrisesunset = new SunriseSunsetCalculator(timeZone, latitude,
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

	/**
	 * Returns the nautical twilight associated to sunrise occurs when the
	 * geometrical center of the Sun is between 6 degrees and 12 degrees below
	 * the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            nautical sunrise for.
	 * @return The nautical sunrise time in HH:MM (24-hour clock) form.
	 */
	public String getNauticalSunrise(Calendar date) {
		return sunrisesunset.computeSunriseTime(NAUTICAL, date);
	}

	/**
	 * Returns the nautical twilight associated to sunrise occurs when the
	 * geometrical center of the Sun is between 6 degrees and 12 degrees below
	 * the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            nautical sunrise for.
	 * @return The nautical sunrise time as a {@code Calendar}
	 */
	public Calendar getNauticalSunriseCalendar(Calendar date) {
		return sunrisesunset.computeSunriseCalendar(NAUTICAL, date);
	}

	/**
	 * Returns the nautical twilight associated to sunset occurs when the
	 * geometrical center of the Sun is between 6 degrees and 12 degrees below
	 * the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            nautical sunset for.
	 * @return The nautical sunset time in HH:MM (24-hour clock) form.
	 */
	public String getNauticalSunset(Calendar date) {
		return sunrisesunset.computeSunsetTime(NAUTICAL, date);
	}

	/**
	 * Returns the nautical twilight associated to sunset occurs when the
	 * geometrical center of the Sun is between 6 degrees and 12 degrees below
	 * the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            nautical sunset for.
	 * @return The nautical sunset time as a {@code Calendar}
	 */
	public Calendar getNauticalSunsetCalendar(Calendar date) {
		return sunrisesunset.computeSunsetCalendar(NAUTICAL, date);
	}

	/**
	 * Returns the civil twilight associated to sunrise occurs when the Sun is
	 * between 0 degrees and 6 degrees below the horizon. In the morning, civil
	 * twilight begins when the Sun is 6 degrees below the horizon and ends at
	 * sunrise. In the evening, it begins at sunset and ends when the Sun
	 * reaches 6 degrees below the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            civil sunrise for.
	 * @return The civil sunrise time in HH:MM (24-hour clock) form.
	 */
	public String getCivilSunrise(Calendar date) {
		return sunrisesunset.computeSunriseTime(CIVIL, date);
	}

	/**
	 * Returns the civil twilight associated to sunrise occurs when the Sun is
	 * between 0 degrees and 6 degrees below the horizon. In the morning, civil
	 * twilight begins when the Sun is 6 degrees below the horizon and ends at
	 * sunrise. In the evening, it begins at sunset and ends when the Sun
	 * reaches 6 degrees below the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            civil sunrise for.
	 * @return The civil sunrise time as a {@code Calendar}
	 */
	public Calendar getCivilSunriseCalendar(Calendar date) {
		return sunrisesunset.computeSunriseCalendar(CIVIL, date);
	}

	/**
	 * Returns the civil twilight associated to sunset occurs when the Sun is
	 * between 0 degrees and 6 degrees below the horizon. In the morning, civil
	 * twilight begins when the Sun is 6 degrees below the horizon and ends at
	 * sunrise. In the evening, it begins at sunset and ends when the Sun
	 * reaches 6 degrees below the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            civil sunset for.
	 * @return The civil sunset time in HH:MM (24-hour clock) form.
	 */
	public String getCivilSunset(Calendar date) {
		return sunrisesunset.computeSunsetTime(CIVIL, date);
	}

	/**
	 * Returns the civil twilight associated to sunset occurs when the Sun is
	 * between 0 degrees and 6 degrees below the horizon. In the morning, civil
	 * twilight begins when the Sun is 6 degrees below the horizon and ends at
	 * sunrise. In the evening, it begins at sunset and ends when the Sun
	 * reaches 6 degrees below the horizon.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            civil sunset for.
	 * @return The civil sunset time as a {@code Calendar}
	 */
	public Calendar getCivilSunsetCalendar(Calendar date) {
		return sunrisesunset.computeSunsetCalendar(CIVIL, date);
	}

	/**
	 * Returns the official sunrise (90deg 50', 90.8333deg) for the given date.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            official sunrise for.
	 * @return The official sunrise time in HH:MM (24-hour clock) form.
	 */
	public String getOfficialSunrise(Calendar date) {
		return sunrisesunset.computeSunriseTime(OFFICIAL, date);
	}

	/**
	 * Returns the official sunrise (90deg 50', 90.8333deg) for the given date.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            official sunrise for.
	 * @return The official sunrise time in HH:MM (24-hour clock) form.
	 */
	public Calendar getOfficialSunriseCalendar(Calendar date) {
		return sunrisesunset.computeSunriseCalendar(OFFICIAL, date);
	}

	/**
	 * Returns the official sunset (90deg 50', 90.8333deg) for the given date.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            official sunset for.
	 * @return The official sunset time in HH:MM (24-hour clock) form.
	 */
	public String getOfficialSunset(Calendar date) {
		return sunrisesunset.computeSunsetTime(OFFICIAL, date);
	}

	/**
	 * Returns the official sunset (90deg 50', 90.8333deg) for the given date.
	 * 
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            official sunset for.
	 * @return The official sunset time as a {@code Calendar}
	 */
	public Calendar getOfficialSunsetCalendar(Calendar date) {
		return sunrisesunset.computeSunsetCalendar(OFFICIAL, date);
	}

	/**
	 * Computes the sunrise for an arbitrary declination.
	 * 
	 * @param latitude
	 *            Coordinates for the location to compute the sunrise
	 * @param longitude
	 *            Coordinates for the location to compute the sunrise for.
	 * @param timeZone
	 *            {@code TimeZone} to compute the sunrise times in.
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            official sunrise for.
	 * @param degrees
	 *            Angle under the horizon for which to compute sunrise. For
	 *            example, "civil sunrise" corresponds to 6 degrees.
	 * @return the requested sunrise time as a {@code Calendar} object.
	 */
	public static Calendar getSunrise(double latitude, double longitude,
			TimeZone timeZone, Calendar date, double degrees) {
		return new SunriseSunsetCalculator(timeZone, new BigDecimal(latitude),
				new BigDecimal(longitude)).computeSunriseCalendar(new Twilight(
				90 - degrees), date);
	}

	/**
	 * Computes the sunset for an arbitrary declination.
	 * 
	 * @param latitude
	 *            Coordinates for the location to compute the sunset
	 * @param longitude
	 *            Coordinates for the location to compute the sunset for.
	 * @param timeZone
	 *            {@code TimeZone} to compute the sunset times in.
	 * @param date
	 *            {@code Calendar} object containing the date to compute the
	 *            official sunset for.
	 * @param degrees
	 *            Angle under the horizon for which to compute sunset. For
	 *            example, "civil sunset" corresponds to 6 degrees.
	 * @return the requested sunset time as a {@code Calendar} object.
	 */
	public static Calendar getSunset(double latitude, double longitude,
			TimeZone timeZone, Calendar date, double degrees) {
		return new SunriseSunsetCalculator(timeZone, new BigDecimal(latitude),
				new BigDecimal(longitude)).computeSunsetCalendar(new Twilight(
				90 - degrees), date);
	}

	/**
	 * The latitude of the specific location
	 * 
	 * @return The latitude of the location
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * The longitude of the specific location
	 * 
	 * @return The longitude of the location
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}
}
