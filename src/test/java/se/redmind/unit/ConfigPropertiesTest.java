package se.redmind.unit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import se.redmind.file.ConfigProperties;

public class ConfigPropertiesTest {

	ConfigProperties rc = new ConfigProperties();
	
	@Test
	public void assertTheReturnedStringIfEmpty(){
		String expected = System.getProperty("user.dir") + File.separator;
		assertEquals(expected, rc.getPath());
	}
	
}
