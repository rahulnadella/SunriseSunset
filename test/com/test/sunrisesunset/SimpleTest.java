package com.test.sunrisesunset;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import com.sunrisesunset.api.Location;
import com.sunrisesunset.api.SunriseSunset;

public class SimpleTest {
	
	@Test
	public void simple()
	{
		Location location = new Location("40.7141667", "-74.0063889");
		SunriseSunset sunriseSunset = new SunriseSunset("America/New_York", null, null);
		
		String officalSunrise = sunriseSunset.getOfficialSunriseForDate(Calendar.getInstance());
		Calendar officalSunset = sunriseSunset.getOfficialSunsetCalendarForDate(Calendar.getInstance());
		
		assertEquals("05:25", officalSunrise);
		assertNotNull(officalSunset);
	}
}
