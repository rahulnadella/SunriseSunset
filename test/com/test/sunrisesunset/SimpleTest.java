package com.test.sunrisesunset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;

import com.sunrisesunset.api.SunriseSunset;

public class SimpleTest {
	
	@Test
	public void simple()
	{
		SunriseSunset sunriseSunset = new SunriseSunset("America/New_York", "40.7141667", "-74.0063889");
		
		String officalSunrise = sunriseSunset.getOfficialSunrise(Calendar.getInstance());
		Calendar officalSunset = sunriseSunset.getOfficialSunsetCalendar(Calendar.getInstance());
		
		assertEquals("05:25", officalSunrise);
		assertNotNull(officalSunset);
	}
}
