package com.sunrisesunset.api;

import java.math.BigDecimal;

/**
 * The definition of {@link Twilight} is the point on the celestial sphere
 * vertically above a given position or observer.
 * 
 * The {@link Twilight} class defines the solar declination used in computing
 * the sunrise/sunset.
 * 
 * @version 1.0
 */
public class Twilight {
	/**
	 * Astronomical sunrise/set is when the sun is 18 degrees below the horizon.
	 */
	public static final Twilight ASTRONOMICAL = new Twilight(108);

	/** Nautical sunrise/set is when the sun is 12 degrees below the horizon. */
	public static final Twilight NAUTICAL = new Twilight(102);

	/**
	 * Civil sunrise/set (dawn/dusk) is when the sun is 6 degrees below the
	 * horizon.
	 */
	public static final Twilight CIVIL = new Twilight(96);

	/**
	 * Official sunrise/set is when the sun is 50' below the horizon.
	 */
	public static final Twilight OFFICIAL = new Twilight(90.8333);

	protected final BigDecimal degrees;

	public Twilight(double degrees) {
		this.degrees = BigDecimal.valueOf(degrees);
	}

	public BigDecimal degrees() {
		return degrees;
	}
}
