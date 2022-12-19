package com.countries;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.countries.util.Arquive;
import com.countries.util.Country;
import com.countries.util.LongNumber;
import com.countries.util.ShortNumber;

public class App {
	public static void main(String[] args) {

		try {

			// create the list from the archive
			List<String> countryCodes = Arquive.readArchive("src//main//resources//coutryCodes.txt");
			List<String> phoneNumbers = Arquive.readArchive(args[0]);

			long qtdShortNumbersOfPortugal = phoneNumbers.stream()
					.filter(phoneNumber -> ShortNumber.isShortNumber.and(ShortNumber.isBeginWithZero)
							.and(ShortNumber.isBlankSpace).and(ShortNumber.isOnlyNumbers).test(phoneNumber))
					.count();

			Map<Integer, String> mapaCountryCodes = Country.mapCountryCodes.apply(countryCodes);

			Function<List<String>, Map<String, Integer>> getQtdPhoneNumberPerCounty = list -> {
				Map<String, Integer> qtdPhoneNumbersPerCountry = new HashMap<String, Integer>();
				for (String countryCode : list) {
					String countryName = "";
					if (mapaCountryCodes.containsKey(Integer.valueOf(countryCode))) {
						countryName = mapaCountryCodes.get(Integer.valueOf(countryCode));
					} else if (mapaCountryCodes.containsKey(Integer.valueOf(countryCode.substring(0, 2)))) {
						countryName = mapaCountryCodes.get(Integer.valueOf(countryCode.substring(0, 2)));
					} else if (mapaCountryCodes.containsKey(Integer.valueOf(countryCode.substring(0, 1)))) {
						countryName = mapaCountryCodes.get(Integer.valueOf(countryCode.substring(0, 1)));
					}
					if (!countryName.equals("")) {
						int qtd = 0;
						if (qtdPhoneNumbersPerCountry.containsKey(countryName)) {
							qtd = qtdPhoneNumbersPerCountry.get(countryName);
						}
						qtd++;
						qtdPhoneNumbersPerCountry.put(countryName, qtd);
					}
				}
				return qtdPhoneNumbersPerCountry;
			};

			Comparator<Integer> outerComparator = (v1, v2) -> {
				if (v1.intValue() > v2.intValue())
					return -1;
				else if (v1.intValue() < v2.intValue())
					return 1;
				else
					return 0;
			};

			// remove whitespace at ends of the String
			phoneNumbers = phoneNumbers.stream().map(phoneNumber -> String.valueOf(phoneNumber.trim()))
					.collect(Collectors.toList());

			phoneNumbers = phoneNumbers
					.stream().filter(phoneNumber -> LongNumber.startWithValidCaracter
							.and(LongNumber.existsWhiteSpaceBetweenCharacterAndCountryCode).test(phoneNumber))
					.collect(Collectors.toList());

			
			phoneNumbers = phoneNumbers.stream().map(phoneNumber -> LongNumber.replaceCharacters.apply(phoneNumber))
					.filter(phoneNumber -> ShortNumber.isOnlyNumbers.and(LongNumber.isLongNumber).test(phoneNumber)).collect(Collectors.toList());

			List<String> possiblesCountryCodes = phoneNumbers.stream().map(phoneNumber -> phoneNumber.substring(0, 3))
					.collect(Collectors.toList());

			Map<String, Integer> qtdPhoneNumbersPerCountry = getQtdPhoneNumberPerCounty.apply(possiblesCountryCodes);

			int portugalCount = 0;
			if (qtdPhoneNumbersPerCountry.containsKey("Portugal")) {
				portugalCount = (int) qtdPhoneNumbersPerCountry.get("Portugal");
			}
			portugalCount += qtdShortNumbersOfPortugal;

			qtdPhoneNumbersPerCountry.put("Portugal", portugalCount);

			System.out.println("#Response.....");
			qtdPhoneNumbersPerCountry.entrySet(). 
					stream().
					sorted(
							Map.Entry.<String, Integer>comparingByValue(outerComparator))
					.forEach(System.out::println);

		} catch (IOException e) {
			System.out.println("Problems processing the file, please check and try again");
			System.out.println("Name = " + e.getClass().getName() + " Message = " + e.getMessage());
		} catch (Exception e) {
			System.out.println("A problem occurred and the application needed to be terminated. Please send this message to support:");
			System.out.println("Name = " + e.getClass().getName() + " Message = " + e.getMessage());
		} 
	}
}
