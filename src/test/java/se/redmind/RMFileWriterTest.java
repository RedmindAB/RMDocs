package test.java.se.redmind;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import se.redmind.file.RMFileWriter;
import se.redmind.structure.Project;
public class RMFileWriterTest {

	Project proj = new Project();
	
	@Test
	public void test_returnFileName() {
		proj.setProjectName("hej");
	//	RMFileWriter rm = new RMFileWriter(".txt");
		String expected = (new Date().toString().replace("CEST","")+" "+ proj.getProjectName());
	//	assertEquals(expected, rm.appendDateToFile(proj));
	}

}
