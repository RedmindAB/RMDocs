package test.java.se.redmind;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import se.redmind.file.ReadConfigProperties;

public class ReadConfigTest {

	ReadConfigProperties rc = new ReadConfigProperties();
	
	@Test(expected = FileNotFoundException.class)	
	public void ifFileIsNotFoundThrowNewFileNotFoundException() {
         rc.setPropFileName("conf");
         rc.getPropValues();
	}
	
	@Test
	public void assertTheReturnedString(){
		String expected = "/Users/Oz/Documents/";
		assertEquals(expected, rc.getPropValues());
	}

}
