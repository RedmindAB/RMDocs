package test.java.se.redmind;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import se.redmind.file.ConfigProperties;

public class ReadConfigTest {

	ConfigProperties rc = new ConfigProperties();
	
	@Test
	public void assertTheReturnedStringIfEmpty(){
		String expected = System.getProperty("user.home");
		assertEquals(expected, rc.getPropValues());
	}
	

}
