package com.countries.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArquiveTest {
	
	@Test
	public void testIfArquiveCountryCodesExists() {
		File path = new File("resources//");        
        List<File> arquives = Arrays.asList(path.listFiles());
        assertEquals("arquive countryCodes.txt found!", 1, arquives.size());
	}
	
	@Test
	public void testIfArquiveInputExists() {
		File path = new File(".");        
        List<File> arquives = Arrays.asList(path.listFiles());
        long inputCount = arquives.stream().filter(arquive -> arquive.getName().equals("input.txt")).count();
        assertEquals("arquive input.txt found!", 1, inputCount);
	}

}
