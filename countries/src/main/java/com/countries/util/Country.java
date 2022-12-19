package com.countries.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Country {

	public static Function<List<String>, Map<Integer, String>> mapCountryCodes = list -> {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (String line : list) {
			Integer key = Integer.parseInt(line.split("-")[1]);
			String value = line.split("-")[0];
			if (!map.containsKey(key))
				map.put(key, value);
		}
		return map;
	};

}
