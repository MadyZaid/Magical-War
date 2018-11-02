package com.amr.rpg.util;

/**
 * @author aeldemerdash
 *
 */
public class NumbersUtil {
	public static Double getRandomDoubleBetweenRange(Double min, Double max) {
		Double x = (Math.random() * ((max - min) + 1)) + min;
		return x;
	}
	
	public static Integer getRandomIntegerBetweenRange(int min, int max) {
		Double x = (Math.random() * ((max - min) + 1)) + min;
		return x.intValue();
	}

}
