package com.sunrisesunset.util;

import static java.lang.Math.PI;
import static java.math.RoundingMode.HALF_EVEN;
import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.ZONE_OFFSET;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

/**
 * The {@code MathUtility} class provides a set of helper methods to enable easy
 * calculation of BigDecimal within the {@code SunriseSunsetUtility}.
 * 
 * @version 1.0
 */
public class MathUtility {
	/**
	 * Returns the day of the year based on the date in a {@link BigDecimal}
	 * 
	 * @param date
	 *            The specific date
	 * @return A {@link BigDecimal} value of the specific year of the date
	 */
	public static BigDecimal getDayOfYear(Calendar date) {
		return new BigDecimal(date.get(DAY_OF_YEAR));
	}

	/**
	 * The offset of the date UTC.
	 * 
	 * @param date
	 * 		   The specific date
	 * @return A {@code BigDecimal} value of the date based on the zone offset
	 */
	public static BigDecimal getUTCOffset(Calendar date) {
		return new BigDecimal(date.get(ZONE_OFFSET)).divide(new BigDecimal(
				3600000), new MathContext(2));
	}

	/**
	 * Returns the arc cosine of a value; the returned angle is in the
     * range 0.0 through <i>pi</i>.  Special case:
     * <ul><li>If the argument is NaN or its absolute value is greater
     * than 1, then the result is NaN.</ul>
	 * 
	 * @param radians
	 * 		  The value in radians
	 * @return A {@code BigDecimal} of the arc cosine in radians
	 */
	public static BigDecimal getArcCosineFor(BigDecimal radians) {
		return setScale(BigDecimal.valueOf(Math.acos(radians.doubleValue())));
	}

	/**
	 * Converts the radians to degrees
	 * 
	 * @param radians
	 * 		  The {@code BigDecimal} value in radians
	 * @return A {@code BigDecimal} in degrees
	 */
	public static BigDecimal convertRadiansToDegrees(BigDecimal radians) {
		return multiplyBy(radians, new BigDecimal(180 / PI));
	}

	/**
	 * Converts the degrees to radians
	 * 
	 * @param degrees
	 * 		  The {@code BigDecimal} value in degrees
	 * @return A {@code BigDecimal} in radians
	 */
	public static BigDecimal convertDegreesToRadians(BigDecimal degrees) {
		return multiplyBy(degrees, BigDecimal.valueOf(PI / 180.0));
	}

	/**
	 * Returns a {@code BigDecimal} whose value is <tt>(this &times;
     * multiplicand)</tt>, and whose scale is {@code (this.scale() +
     * multiplicand.scale())}.
	 * 
	 * @return {@code this * multiplicand}
	 */
	protected static BigDecimal multiplyBy(BigDecimal multiplicand,
			BigDecimal multiplier) {
		return setScale(multiplicand.multiply(multiplier));
	}

	/**
	 * Returns a {@code BigDecimal} whose value is {@code (this /
     * divisor)}, and whose scale is as specified.  If rounding must
     * be performed to generate a result with the specified scale, the
     * specified rounding mode is applied.
     * 
	 * @return {@code this / divisor}
	 */
	protected static BigDecimal divideBy(BigDecimal dividend, BigDecimal divisor) {
		return dividend.divide(divisor, 4, HALF_EVEN);
	}

	/**
	 * Returns a {@code BigDecimal} whose scale is the specified value, and
	 * whose unscaled value is determined by multiplying or dividing this
	 * {@code BigDecimal}'s unscaled value by the appropriate power of ten to
	 * maintain its overall value. If the scale is reduced by the operation, the
	 * unscaled value must be divided (rather than multiplied), and the value
	 * may be changed; in this case, the specified rounding mode is applied to
	 * the division.
	 * 
	 * @param bigDecimal
	 *            The value of the BigDecimal
	 * @return a {@code BigDecimal} whose scale is the specified value, and
	 *         whose unscaled value is determined by multiplying or dividing
	 *         this {@code BigDecimal}'s unscaled value by the appropriate power
	 *         of ten to maintain its overall value.
	 */
	protected static BigDecimal setScale(BigDecimal bigDecimal) {
		return bigDecimal.setScale(4, HALF_EVEN);
	}
}
