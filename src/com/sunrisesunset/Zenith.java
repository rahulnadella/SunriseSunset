package com.sunrisesunset;

import java.math.BigDecimal;

/**
 * The definition of Zenith is the point on the celestial sphere vertically
 * above a given position or observer.
 * 
 * The {@link Zenith} class defines the solar declination used in computing the
 * sunrise/sunset.
 * 
 * @version 1.0
 */
public class Zenith {
	/**
	 * Astronomical sunrise/set is when the sun is 18 degrees below the horizon.
	 */
	public static final Zenith ASTRONOMICAL = new Zenith(108);

	/** Nautical sunrise/set is when the sun is 12 degrees below the horizon. */
	public static final Zenith NAUTICAL = new Zenith(102);

	/**
	 * Civil sunrise/set (dawn/dusk) is when the sun is 6 degrees below the
	 * horizon.
	 */
	public static final Zenith CIVIL = new Zenith(96);

	/**
	 * Official sunrise/set is when the sun is 50' below the horizon.
	 */
	public static final Zenith OFFICIAL = new Zenith(90.8333);

	protected final BigDecimal degrees;

	public Zenith(double degrees) {
		this.degrees = BigDecimal.valueOf(degrees);
	}

	public BigDecimal degrees() {
		return degrees;
	}
}
