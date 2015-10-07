package test.java.se.redmind;

import static org.junit.Assert.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

import se.redmind.file.RMFileWriter;
import se.redmind.structure.Project;



public class RMFileWriterTest {

	Project proj = new Project();
	RMFileWriter rm;

//	@Test
//	public void test_returnFileName() {
//		rm = new RMFileWriter(" ", "/Users/Oz/Documents/");
//		proj.setProjectName("hej");
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//		String date = sd.format(new Date());
//		String expected = "hej" +"-"+ date;
//		assertEquals(expected, rm.appendDateToFile(proj));
//	}

	@Test
	public void testIfMethodreturnsFormatToText(){
//	    rm = new RMFileWriter(".txt", "/Users/Oz/Documents/");
//	    proj.setProjectName("nn");
	    
	
	}
	
	@Test
	public void testIfMethodParsesArgsToJson(){
//		rm = new RMFileWriter(".json","/Users/Oz/Documents/");
		
	}
}



