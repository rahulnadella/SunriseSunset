package com.sunrisesunset.api;

import static com.sunrisesunset.api.Twilight.ASTRONOMICAL;
import static com.sunrisesunset.api.Twilight.CIVIL;
import static com.sunrisesunset.api.Twilight.NAUTICAL;
import static com.sunrisesunset.api.Twilight.OFFICIAL;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;

import com.sunrisesunset.util.SunsetSunriseUtility;

public class SunriseSunset {

	protected BigDecimal latitude;
	protected BigDecimal longitude;
	
	protected SunsetSunriseUtility solarEvent;
	
	public SunriseSunset(String timeZoneIdentifier, BigDecimal latitude, BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.solarEvent = new SunsetSunriseUtility(timeZoneIdentifier, latitude, longitude);
	}
	
	public SunriseSunset(TimeZone timeZone, BigDecimal latitude, BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.solarEvent = new SunsetSunriseUtility(timeZone, latitude, longitude);
	}
	
	public String getAstronomicalSunriseForDate(Calendar date) {
		return solarEvent.computeSunriseTime(ASTRONOMICAL, date);
	}
	
	public Calendar getAstronomicalSunriseCalendarForDate(Calendar date) {
		return solarEvent.computeSunriseCalendar(ASTRONOMICAL, date);
	}
	
	public String getAstronomicalSunsetForDate(Calendar date) {
		return solarEvent.computeSunsetTime(ASTRONOMICAL, date);
	}
	
	public Calendar getAstronomicalSunsetCalendarForDate(Calendar date) {
		return solarEvent.computeSunsetCalendar(ASTRONOMICAL, date);
	}
	
	public String getNauticalSunriseForDate(Calendar date) {
		return solarEvent.computeSunriseTime(NAUTICAL, date);
	}
	
	public Calendar getNauticalSunriseCalendarForDate(Calendar date) {
		return solarEvent.computeSunriseCalendar(NAUTICAL, date);
	}
	
	public String getNauticalSunsetForDate(Calendar date) {
		return solarEvent.computeSunsetTime(NAUTICAL, date);
	}
	
	public Calendar getNauticalSunsetCalendarForDate(Calendar date) {
		return solarEvent.computeSunsetCalendar(NAUTICAL, date);
	}
	
	public String getCivilSunriseForDate(Calendar date) {
		return solarEvent.computeSunriseTime(CIVIL, date);
	}
	
	public Calendar getCivilSunriseCalendarForDate(Calendar date) {
		return solarEvent.computeSunriseCalendar(CIVIL, date);
	}
	
	public String getCivilSunsetForDate(Calendar date) {
		return solarEvent.computeSunsetTime(CIVIL, date);
	}
	
	public Calendar getCivilSunsetCalendarForDate(Calendar date) {
		return solarEvent.computeSunsetCalendar(CIVIL, date);
	}
	
	public String getOfficialSunriseForDate(Calendar date) {
		return solarEvent.computeSunriseTime(OFFICIAL, date);
	}
	
	public Calendar getOfficialSunriseCalendarForDate(Calendar date) {
		return solarEvent.computeSunriseCalendar(OFFICIAL, date);
	}
	
	public String getOfficialSunsetForDate(Calendar date) {
		return solarEvent.computeSunsetTime(OFFICIAL, date);
	}
	
	public Calendar getOfficialSunsetCalendarForDate(Calendar date) {
		return solarEvent.computeSunsetCalendar(OFFICIAL, date);
	}
	
	public static Calendar getSunrise(double latitude, double longitude, TimeZone timeZone, Calendar date, double degrees) {
		return new SunsetSunriseUtility(timeZone, new BigDecimal(latitude), new BigDecimal(longitude)).computeSunriseCalendar(new Twilight(90 - degrees), date);
	}
	
	public static Calendar getSunset(double latitude, double longitude, TimeZone timeZone, Calendar date, double degrees) {
        return new SunsetSunriseUtility(timeZone, new BigDecimal(latitude), new BigDecimal(longitude)).computeSunsetCalendar(new Twilight(90 - degrees), date);
    }
	
	public BigDecimal getLatitude() {
		return latitude;
	}
	
	public BigDecimal getLongitude() {
		return longitude;
	}
}
