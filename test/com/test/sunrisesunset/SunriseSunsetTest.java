package com.test.sunrisesunset;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.sunrisesunset.Location;
import com.sunrisesunset.SunriseSunset;

public class SunriseSunsetTest extends BaseTestCase {
	private SunriseSunset calc;

	@Before
	public void setup() {
		// November 1, 2008
		super.setup(10, 1, 2008);
		calc = new SunriseSunset(location, "America/New_York");
	}

	@Test
	public void testComputeAstronomicalSunrise() {
		assertTimeEquals("06:01",
				calc.getAstronomicalSunriseForDate(eventDate), eventDate
						.getTime().toString());
	}

	@Test
	public void testComputeAstronomicalSunset() {
		assertTimeEquals("19:32", calc.getAstronomicalSunsetForDate(eventDate),
				eventDate.getTime().toString());
	}

	@Test
	public void testComputeNauticalSunrise() {
		assertTimeEquals("06:33", calc.getNauticalSunriseForDate(eventDate),
				eventDate.getTime().toString());
	}

	@Test
	public void testComputeNauticalSunset() {
		assertTimeEquals("19:00", calc.getNauticalSunsetForDate(eventDate),
				eventDate.getTime().toString());
	}

	@Test
	public void testComputeCivilSunrise() {
		assertTimeEquals("07:05", calc.getCivilSunriseForDate(eventDate),
				eventDate.getTime().toString());
	}

	@Test
	public void testComputeCivilSunset() {
		assertTimeEquals("18:28", calc.getCivilSunsetForDate(eventDate),
				eventDate.getTime().toString());
	}

	@Test
	public void testComputeOfficialSunrise() {
		assertTimeEquals("07:33", calc.getOfficialSunriseForDate(eventDate),
				eventDate.getTime().toString());
	}

	@Test
	public void testComputeOfficialSunset() {
		assertTimeEquals("18:00", calc.getOfficialSunsetForDate(eventDate),
				eventDate.getTime().toString());
	}

	@Test
	public void testSpecificDateLocationAndTimezone() {
		Location loc = new Location("55.03", "82.91");
		SunriseSunset calculator = new SunriseSunset(loc, "GMT");

		Calendar calendar = Calendar.getInstance();
		calendar.set(2012, 4, 7);

		String officialSunriseForDate = calculator
				.getOfficialSunriseForDate(calendar);
		assertEquals("22:35", officialSunriseForDate);

		Calendar officialSunriseCalendarForDate = calculator
				.getOfficialSunriseCalendarForDate(calendar);
		assertEquals(22,
				officialSunriseCalendarForDate.get(Calendar.HOUR_OF_DAY));
		assertEquals(35, officialSunriseCalendarForDate.get(Calendar.MINUTE));
		assertEquals(6,
				officialSunriseCalendarForDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testNonIntegerTimezoneOffset() {
		Location loc = new Location("22.56", "88.36");
		SunriseSunset calculator = new SunriseSunset(loc, "Asia/Kolkata");

		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 12, 15);

		String officialSunriseForDate = calculator
				.getOfficialSunriseForDate(calendar);
		assertEquals("06:19", officialSunriseForDate);
	}
}
