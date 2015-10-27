package se.redmind;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.TemporaryFolder;

import se.redmind.file.FileFinder;

public class FileFinderTest {

	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	FileFinder ff = new FileFinder(".java");

	@Test
	public void validateSizeOfListWhenItemAddedIsCorrect(){
		ff.addFileToList(new File("hejhej.java"));
		assertEquals(1, ff.getFileList().size());
		ff.addFileToList(new File("hojhoj.java"));
		assertEquals(2, ff.getFileList().size());
	}
	
	@Test
	public void validateThatSystemExitsIfListIsEmpty(){
		ff.addFileToList(new File("hejhej.js"));
		exit.expectSystemExitWithStatus(1);
		ff.getFileList();
	}

	@Test
	public void testThatFileFormatIsCorrect(){
		assertEquals(".java", ff.getFileFormat());
	}

//	@Test
//	public void assertRecursiveMethodCallsIfFileIsADir() throws IOException {
//		File tempFile = testFolder.newFile("file.txt");
//		File tempFolder = testFolder.newFolder("folder");
//		ff.pathWalker(tempFolder);
//		System.out.println(tempFile.getAbsolutePath());
//	}
}
