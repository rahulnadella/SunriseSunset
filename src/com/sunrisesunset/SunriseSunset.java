package com.sunrisesunset;

import java.util.Calendar;
import java.util.TimeZone;

public class SunriseSunset {

	protected Location location;
	
	protected SolarEvent solarEvent;
	
	public SunriseSunset(Location location, String timeZoneIdentifier) {
		this.location = location;
		this.solarEvent = new SolarEvent(location, timeZoneIdentifier);
	}
	
	public SunriseSunset(Location location, TimeZone timeZone) {
		this.location = location;
		this.solarEvent = new SolarEvent(location, timeZone);
	}
	
	public String getAstronomicalSunriseForDate(Calendar date) {
		return solarEvent.computeSunriseTime(Zenith.ASTRONOMICAL, date);
	}
	
	public Calendar getAstronomicalSunriseCalendarForDate(Calendar date) {
		return solarEvent.computeSunriseCalendar(Zenith.ASTRONOMICAL, date);
	}
	
	public String getAstronomicalSunsetForDate(Calendar date) {
		return solarEvent.computeSunsetTime(Zenith.ASTRONOMICAL, date);
	}
	
	public Calendar getAstronomicalSunsetCalendarForDate(Calendar date) {
		return solarEvent.computeSunsetCalendar(Zenith.ASTRONOMICAL, date);
	}
	
	public String getNauticalSunriseForDate(Calendar date) {
		return solarEvent.computeSunriseTime(Zenith.NAUTICAL, date);
	}
	
	public Calendar getNauticalSunriseCalendarForDate(Calendar date) {
		return solarEvent.computeSunriseCalendar(Zenith.NAUTICAL, date);
	}
	
	public String getNauticalSunsetForDate(Calendar date) {
		return solarEvent.computeSunsetTime(Zenith.NAUTICAL, date);
	}
	
	public Calendar getNauticalSunsetCalendarForDate(Calendar date) {
		return solarEvent.computeSunsetCalendar(Zenith.NAUTICAL, date);
	}
	
	public String getCivilSunriseForDate(Calendar date) {
		return solarEvent.computeSunriseTime(Zenith.CIVIL, date);
	}
	
	public Calendar getCivilSunriseCalendarForDate(Calendar date) {
		return solarEvent.computeSunriseCalendar(Zenith.CIVIL, date);
	}
	
	public String getCivilSunsetForDate(Calendar date) {
		return solarEvent.computeSunsetTime(Zenith.CIVIL, date);
	}
	
	public Calendar getCivilSunsetCalendarForDate(Calendar date) {
		return solarEvent.computeSunsetCalendar(Zenith.CIVIL, date);
	}
	
	public String getOfficialSunriseForDate(Calendar date) {
		return solarEvent.computeSunriseTime(Zenith.OFFICIAL, date);
	}
	
	public Calendar getOfficialSunriseCalendarForDate(Calendar date) {
		return solarEvent.computeSunriseCalendar(Zenith.OFFICIAL, date);
	}
	
	public String getOfficialSunsetForDate(Calendar date) {
		return solarEvent.computeSunsetTime(Zenith.OFFICIAL, date);
	}
	
	public Calendar getOfficialSunsetCalendarForDate(Calendar date) {
		return solarEvent.computeSunsetCalendar(Zenith.OFFICIAL, date);
	}
	
	public static Calendar getSunrise(double latitude, double longitude, TimeZone timeZone, Calendar date, double degrees) {
		return new SolarEvent(new Location(latitude, longitude), timeZone).computeSunriseCalendar(new Zenith(90 - degrees), date);
	}
	
	public static Calendar getSunset(double latitude, double longitude, TimeZone timeZone, Calendar date, double degrees) {
        return new SolarEvent(new Location(latitude, longitude), timeZone).computeSunsetCalendar(new Zenith(90 - degrees), date);
    }
	
	public Location getLocation() {
		return location;
	}
}
