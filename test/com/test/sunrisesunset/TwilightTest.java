package com.test.sunrisesunset;

import static com.sunrisesunset.api.Twilight.ASTRONOMICAL;
import static com.sunrisesunset.api.Twilight.CIVIL;
import static com.sunrisesunset.api.Twilight.NAUTICAL;
import static com.sunrisesunset.api.Twilight.OFFICIAL;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sunrisesunset.api.Twilight;

/**
 * The {@link TwilightTest} class tests the functionality within the
 * {@link Twilight} class
 * 
 * @version 1.0
 */
public class TwilightTest {

	/**
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {

	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * {@link com.sunrisesunset.api.Twilight.ASTRONOMICAL}
	 */
	@Test
	public void testAstronomicalTwilight() {
		assertEquals(108.0, ASTRONOMICAL.degrees().doubleValue(), 0.1);
	}

	/**
	 * {@link com.sunrisesunset.api.Twilight.CIVIL}
	 */
	@Test
	public void testCivilTwilight() {
		assertEquals(96.0, CIVIL.degrees().doubleValue(), 0.1);
	}

	/**
	 * {@link com.sunrisesunset.api.Twilight.NAUTICAL}
	 */
	@Test
	public void testNauticalTwilight() {
		assertEquals(102.0, NAUTICAL.degrees().doubleValue(), 0.1);
	}

	/**
	 * {@link com.sunrisesunset.api.Twilight.OFFICAL}
	 */
	@Test
	public void testOfficialTwilight() {
		assertEquals(90.8333, OFFICIAL.degrees().doubleValue(), 0.00001);
	}
}
