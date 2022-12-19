package com.countries.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Arquive {

	public static List<String> readArchive(String fileName) throws IOException {
		return Files.readAllLines(Path.of(fileName));
	}
}
