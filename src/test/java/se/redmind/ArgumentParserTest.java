package test.java.se.redmind;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import se.redmind.file.ArgumentParser;

public class ArgumentParserTest {

	ArgumentParser arg;

	@Before
	public void before() {
		String[] args = { "-p", "/Users/victormattsson/Documents", "-a", "@rm", "-f", ".java", "-o", ".txt" };
		arg = new ArgumentParser(args);
	}

//	@Test
//	public void testTheReturnedStringOfToStringMethod() {
//		arg.parse();
//		assertEquals("path: /Users/victormattsson/Documents Annotation: @rm Format to read: "
//				+ ".java Format to write: .txt", arg.toString());
//	}

	@Test
	public void assertThatTheReturnedFileFormatIsCorrect() {
		arg.parse();
		assertEquals(".java", arg.getReadFormat());
	}

	@Test
	public void assertCorrectErrorMessageIfPathIsNull() {
		arg.validatePath(null);
		assertEquals("Please enter a path\n", arg.getErr().toString());
	}

	@Test
	public void assertCorrectErrorMessageIfPathIsNotAPath() {
		arg.validatePath(new File("/ahuahua/"));
		assertEquals("Path does not exist: /ahuahua\n", arg.getErr().toString());
	}

	@Test
	public void assertCorrectErrorMessageIfAnnotationIsNull() {
		String str = null;
		arg.validateAnnotation(str);
		assertEquals("Invalid annotation: null, add \"-a annotation\" as argument." + "\n", arg.getErr().toString());
	}

	@Test
	public void assertCorrectErrorMessageIfAnnotationIsNotCorrect() {
		String str = "@oscar";
		arg.validateAnnotation(str);
		assertEquals("Invalid annotation: " + str + "\n", arg.getErr().toString());
	}

	@Test
	public void assertCorrectErrorMessageIfReadFormatIsNull() {
		String str = null;
		arg.validateReadFormat(str);
		assertEquals("Invalid read format: " + str + "\n", arg.getErr().toString());
	}

	@Test
	public void assertCorrectErrorMessageIfReadFormatIsInvalid() {
		String str = "@oscar";
		arg.validateReadFormat(str);
		assertEquals("Invalid read format: [" + str + "]. Valid formats: " + arg.getValidReadFormats() + "\n", arg.getErr().toString());
	}

	@Test
	public void assertCorrectErrorMessageIfOutputFormatIsNull() {
		String str = null;
		arg.validateOutputFormat(str);
		assertEquals("Invalid output format: " + str + "\n", arg.getErr().toString());
	}

	@Test
	public void assertCorrectErrorMessageIfOutputFormatIsInvalid() {
		String str = ".klasse";
		arg.validateOutputFormat(str);
		assertEquals("Invalid output format: [.klasse]. Valid formats: "+ arg.getValidOutputFormats() +"\n", arg.getErr().toString());
	}

}
