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
package test.java;

import static main.java.Twilight.ASTRONOMICAL;
import static main.java.Twilight.CIVIL;
import static main.java.Twilight.NAUTICAL;
import static main.java.Twilight.OFFICIAL;
import static org.junit.Assert.assertEquals;
import main.java.Twilight;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
