package se.redmind.unit;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import se.redmind.file.ArgumentParser;

public class ArgumentParserTest {

	ArgumentParser arg;
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    String path = System.getProperty("user.dir");

	@Before
	public void before() {

		String[] args = { "-p", path, "-a", "@rm", "-f", ".java", "-o", ".txt" };
		arg = new ArgumentParser(args);
	}

	@Test
	public void assertThatTheReturnedFileFormatIsCorrect() {
		arg.parse();
		assertEquals(".java", arg.getReadFormat());
	}

	@Test
	public void assertCorrectErrorMessageIfPathIsNull() {
		arg.validatePath(null);
		assertEquals("Please enter a path\n", arg.getErrorMessage());
	}

	@Test
	public void assertCorrectErrorMessageIfPathIsNotAPath() {
		arg.validatePath(new File("/ahuahua/"));
		assertEquals("Path does not exist: /ahuahua\n", arg.getErrorMessage());
	}

	@Test
	public void assertCorrectErrorMessageIfAnnotationIsNull() {
		String str = null;
		arg.validateAnnotation(str);
		assertEquals("Invalid annotation: null, add \"-a annotation\" as argument." + "\n", arg.getErrorMessage());
	}

	@Test
	public void assertCorrectErrorMessageIfAnnotationIsNotCorrect() {
		String str = "@oscar";
		arg.validateAnnotation(str);
		assertEquals("Invalid annotation: " + str + "\n", arg.getErrorMessage());
	}

	@Test
	public void assertCorrectErrorMessageIfReadFormatIsNull() {
		String str = null;
		arg.validateReadFormat(str);
		assertEquals("Invalid read format: " + str + "\n", arg.getErrorMessage());
	}

	@Test
	public void assertCorrectErrorMessageIfReadFormatIsInvalid() {
		String str = "@oscar";
		arg.validateReadFormat(str);
		assertEquals("Invalid read format: [" + str + "]. Valid formats: " + arg.getValidReadFormats() + "\n", arg.getErrorMessage());
	}

	@Test
	public void assertCorrectErrorMessageIfOutputFormatIsNull() {
		List<String> list = new ArrayList<>();
		arg.validateOutputFormats(list);
		assertEquals("No output formats given.", arg.getErrorMessage());
	}

	@Test
	public void assertCorrectErrorMessageIfOutputFormatIsInvalid() {
		List<String> list = new ArrayList<>();
		list.add(".klasse");
		
		arg.validateOutputFormats(list);
		assertEquals("Invalid output format: [.klasse]. Valid formats: "+ arg.getValidOutputFormats() +"\n", arg.getErrorMessage());
	}

	@Test
	public void assertSystemExitifNoPathIsGiven(){
		String[] args = {"-a", "@rm", "-f", ".java", "-o", ".txt", "-p" };
		arg = new ArgumentParser(args);
        exit.expectSystemExitWithStatus(1);
        arg.parse();

	}

    @Test
    public void assertSystemExitifNoFileIsGiven(){
        String[] args = {"-p", "/Users/username/Documents", "-a", "@rm", "-o", ".txt", "-f"  };
        arg = new ArgumentParser(args);
        exit.expectSystemExitWithStatus(1);
        arg.parse();

    }

    @Test
    public void assertSystemExitifNoAnnotationIsGiven(){
        String[] args = {"-p", "/Users/username/Documents", "-f", ".java", "-o", ".txt", "-a"};
        arg = new ArgumentParser(args);
        exit.expectSystemExitWithStatus(1);
        arg.parse();
    }

    @Test
    public void assertSystemExitifNoOutputIsGiven(){
        String[] args = {"-p", "/Users/username/Documents", "-a", "@rm", "-f", ".java", "-o"};
        arg = new ArgumentParser(args);
        exit.expectSystemExitWithStatus(1);
        arg.parse();
    }

    @Test
    public void assertSystemExitifOutFormatListIsEmpty(){
        String[] args = {"-p", "/Users/username/Documents", "-f", ".java", "-o", "-a"};
        arg = new ArgumentParser(args);
        exit.expectSystemExitWithStatus(1);
        arg.parse();
    }

    @Test
    public void testReturnPathMethod(){
        arg.parse();
        assertEquals(new File(path), arg.getPath());
        arg.setPath(null);
        assertNull(arg.getPath());
    }

    @Test
    public void testReturnedSearchStringIsCorrect(){
        String[] args = { "-p", path, "-o", ".txt", "-s", "Author"};
        arg = new ArgumentParser(args);
        arg.parse();
        String[] str = arg.getSearchStringArray();
        assertEquals("Author", str[0]);
    }

    @Test
    public void testReturnedSearchStringIsCorrect2(){
        String[] args = { "-p", path, "-o", ".txt", "-s", "Author Step H"};
        arg = new ArgumentParser(args);
        arg.parse();
        String[] str = arg.getSearchStringArray();
        assertEquals("Author", str[0]);
        assertEquals("Step", str[1]);
        assertEquals("H", str[2]);
    }

    @Test
    public void testValidateSearchStringMethod(){
        String[] args = { "-p", path, "-o", ".txt", "-s", " "};
        arg = new ArgumentParser(args);
        exit.expectSystemExitWithStatus(1);
        arg.parse();
    }


}
