package test.java.se.redmind;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import se.redmind.file.ReadConfigProperties;

public class ReadConfigTest {

	ReadConfigProperties rc = new ReadConfigProperties();
	
	@Test
	public void assertTheReturnedStringIfEmpty(){
		String expected = System.getProperty("user.home");
		assertEquals(expected, rc.getPropValues());
	}
	

}
