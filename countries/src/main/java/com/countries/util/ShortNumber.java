package com.countries.util;

import java.util.function.Predicate;

public class ShortNumber {

	// creating predicates to validate the shortNumber
	public static Predicate<String> isShortNumber = s -> s.length() >= 4 && s.length() <= 6;
	public static Predicate<String> isBlankSpace = s -> s.indexOf(" ") == -1;
	public static Predicate<String> isBeginWithZero = s -> !s.startsWith("0");
	public static Predicate<String> isOnlyNumbers = s -> s.matches("\\d+");

}
