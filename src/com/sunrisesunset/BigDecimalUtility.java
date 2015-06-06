package com.sunrisesunset;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;

public class BigDecimalUtility {

	protected static BigDecimal getDayOfYear(Calendar date) {
		return new BigDecimal(date.get(Calendar.DAY_OF_YEAR));
	}

	protected static BigDecimal getUTCOffset(Calendar date) {
		return new BigDecimal(date.get(Calendar.ZONE_OFFSET)).divide(
				new BigDecimal(3600000), new MathContext(2));
	}

	protected static BigDecimal getArcCosineFor(BigDecimal radians) {
		return setScale(BigDecimal.valueOf(Math.acos(radians.doubleValue())));
	}

	protected static BigDecimal convertRadiansToDegrees(BigDecimal radians) {
		return multiplyBy(radians, new BigDecimal(180 / Math.PI));
	}

	protected static BigDecimal convertDegreesToRadians(BigDecimal degrees) {
		return multiplyBy(degrees, BigDecimal.valueOf(Math.PI / 180.0));
	}

	protected static BigDecimal multiplyBy(BigDecimal multiplicand,
			BigDecimal multiplier) {
		return setScale(multiplicand.multiply(multiplier));
	}

	protected static BigDecimal divideBy(BigDecimal dividend, BigDecimal divisor) {
		return dividend.divide(divisor, 4, RoundingMode.HALF_EVEN);
	}

	protected static BigDecimal setScale(BigDecimal bigDecimal) {
		return bigDecimal.setScale(4, RoundingMode.HALF_EVEN);
	}
}
