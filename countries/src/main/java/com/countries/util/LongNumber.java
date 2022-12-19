package com.countries.util;

import java.util.function.Function;
import java.util.function.Predicate;

public class LongNumber {

	public static Function<String, String> replaceCharacters = phoneNumber -> {
		phoneNumber = phoneNumber.replace("+", "");
		phoneNumber = phoneNumber.replace("00", "");
		phoneNumber = phoneNumber.replace(" ", "");
		return phoneNumber;
	};

	public static Predicate<String> startWithValidCaracter = s -> s.startsWith("+") || s.startsWith("00");
	public static Predicate<String> existsWhiteSpaceBetweenCharacterAndCountryCode = s -> {
		if ((s.startsWith("+") && s.charAt(1) == ' ') || (s.startsWith("00") && s.charAt(2) == ' '))
			return false;
		return true;
	};
	public static Predicate<String> isLongNumber = s -> s.length() >= 9 && s.length() <= 14;

}
