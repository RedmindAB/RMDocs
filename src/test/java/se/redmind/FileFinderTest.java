package test.java.se.redmind;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import se.redmind.file.FileFinder;

public class FileFinderTest {

	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	FileFinder ff = new FileFinder(".java");

	@Test
	public void validateSizeOfListWhenItemAddedIsCorrect(){
		ff.addToList(new File("hejhej.java"));
		assertEquals(1, ff.getFileList().size());
		ff.addToList(new File("hojhoj.java"));
		assertEquals(2, ff.getFileList().size());
	}
	
	@Test
	public void validateThatSystemExitsIfListIsEmpty(){
		ff.addToList(new File("hejhej.js"));
		exit.expectSystemExitWithStatus(1);
		ff.getFileList();
	}
}
