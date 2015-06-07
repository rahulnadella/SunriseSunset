package com.sunrisesunset.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;

public class TimeUtility {

	public static BigDecimal getDayOfYear(Calendar date) {
		return new BigDecimal(date.get(Calendar.DAY_OF_YEAR));
	}

	public static BigDecimal getUTCOffset(Calendar date) {
		return new BigDecimal(date.get(Calendar.ZONE_OFFSET)).divide(
				new BigDecimal(3600000), new MathContext(2));
	}

	public static BigDecimal getArcCosineFor(BigDecimal radians) {
		return setScale(BigDecimal.valueOf(Math.acos(radians.doubleValue())));
	}

	public static BigDecimal convertRadiansToDegrees(BigDecimal radians) {
		return multiplyBy(radians, new BigDecimal(180 / Math.PI));
	}

	public static BigDecimal convertDegreesToRadians(BigDecimal degrees) {
		return multiplyBy(degrees, BigDecimal.valueOf(Math.PI / 180.0));
	}

	public static BigDecimal multiplyBy(BigDecimal multiplicand,
			BigDecimal multiplier) {
		return setScale(multiplicand.multiply(multiplier));
	}

	public static BigDecimal divideBy(BigDecimal dividend, BigDecimal divisor) {
		return dividend.divide(divisor, 4, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal setScale(BigDecimal bigDecimal) {
		return bigDecimal.setScale(4, RoundingMode.HALF_EVEN);
	}
}
