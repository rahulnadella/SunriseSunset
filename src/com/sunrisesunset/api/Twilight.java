package com.sunrisesunset.api;

import java.math.BigDecimal;

/**
 * Twilight is defined according to the solar elevation angle θs, which is the
 * position of the geometric center of the sun relative to the horizon. There
 * are three established and widely accepted subcategories of twilight: civil
 * twilight (brightest), nautical twilight, and astronomical twilight (darkest).
 * 
 * The {@link Twilight} class defines the solar declination used in computing
 * the sunrise/sunset.
 * 
 * @version 1.0
 */
public class Twilight {
	/*
	 * Morning astronomical twilight is defined to begin when the center of the
	 * sun is geometrically 18 degrees below the horizon and to end at sunrise.
	 * Evening astronomical twilight is defined to begin at sunset and end when
	 * the center of the sun is 18 degrees below the horizon. From the end of
	 * astronomical twilight in the evening to the beginning of astronomical
	 * twilight in the morning, the sky (away from urban light pollution,
	 * moonlight, auroras, and other sources of light in the sky) is dark enough
	 * for nearly all astronomical observations. Some critical observations,
	 * such as of faint diffuse items such as nebulae and galaxies, may require
	 * that the sun be more than 18° below the horizon. Because of light
	 * pollution, some localities may never have the opportunity to experience
	 * truly dark skies.
	 */
	public static final Twilight ASTRONOMICAL = new Twilight(108);

	/*
	 * Morning nautical twilight is defined to begin when the center of the sun
	 * is geometrically 12 degrees below the horizon and to end at sunrise.
	 * Evening nautical twilight is defined to begin at sunset and end when the
	 * center of the sun is 12 degrees below the horizon. In general, nautical
	 * twilight ends when navigation via the horizon at sea is no longer
	 * possible.
	 */
	public static final Twilight NAUTICAL = new Twilight(102);

	/*
	 * Morning civil twilight begins when the geometric center of the sun is 6°
	 * below the horizon (civil dawn) and ends at sunrise. Evening civil
	 * twilight begins at sunset and ends when the geometric center of the sun
	 * reaches 6° below the horizon (civil dusk). Civil twilight can also be
	 * described as the limit at which twilight illumination is sufficient,
	 * under clear weather conditions, for terrestrial objects to be clearly
	 * distinguished; at the beginning of morning civil twilight, or end of
	 * evening civil twilight, the horizon is clearly defined and the brightest
	 * stars are visible under clear atmospheric conditions.
	 */
	public static final Twilight CIVIL = new Twilight(96);

	/*
	 * Official sunrise/set is when the sun is 50' below the horizon.
	 */
	public static final Twilight OFFICIAL = new Twilight(90.8333);

	protected final BigDecimal degrees;

	/**
	 * Sets the degrees
	 * 
	 * @param degrees
	 *            The {@code BigDecimal} as degrees to be set to
	 */
	public Twilight(double degrees) {
		this.degrees = BigDecimal.valueOf(degrees);
	}

	/**
	 * Returns the degrees
	 * 
	 * @return The degrees in {@code BigDecimal}
	 */
	public BigDecimal degrees() {
		return degrees;
	}
}
