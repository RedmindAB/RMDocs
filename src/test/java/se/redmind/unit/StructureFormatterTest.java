package se.redmind.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.redmind.file.ArgumentParser;
import se.redmind.structure.Method;
import se.redmind.structure.StructureFormatter;
import se.redmind.util.Conditions;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StructureFormatterTest {

	StructureFormatter sf;
	ArgumentParser arg;
    File file = new File(System.getProperty("user.dir") + "/TestProject/Mock Project/Mock Project/src/se/redmind/mockpackage1/MockTestClass1.java");
    StringBuilder sb = new StringBuilder();
    String[] searchArray = {"Step", "Gurkan"};
    List<String> commentList = new ArrayList<>(Arrays.asList("Author: Victor Mattsson", "Date: 2015-11-05", "Summary: A Comment", "Step: A Step"));

	@Before
	public void before() throws URISyntaxException {
		String[] args = { "-p", "/RMDocs/src/test/java/se/redmind/Testfile.java", "-o", ".json" };
		arg = new ArgumentParser(args);
	}
	
    @Test
    public void ifLineIsNotAMethodReturnFalse(){
        assertFalse(Conditions.isAMethod("public inte en metod(){"));
    }

	@Test
	public void returnedObjectIsAStringBuilder(){
        sf = new StructureFormatter("@rm");
        assertEquals(StringBuilder.class, sf.readFileToStringBuilder(file).getClass());
    }

    @Test
    public void returnedObjectIsAStringArray(){
        sf = new StructureFormatter("@rm");
        assertEquals(String[].class, sf.toArray(sb).getClass());
    }

    @Test
    public void testCheckForMissingComment(){
        Method method = new Method();
        method.setMethodName("testMethod");
        sf = new StructureFormatter("@rm",searchArray);
        sf.checkForMissingComment(commentList, method);
        assertEquals(1 ,sf.getMethodsMissingAnnotations().size());
        assertEquals("Gurkan", sf.getMethodsMissingAnnotations().get("testMethod"));

        commentList.remove(3);
        sf.checkForMissingComment(commentList, method);
        assertEquals(1, sf.getMethodsMissingAnnotations().size());
        assertEquals("Gurkan, Step", sf.getMethodsMissingAnnotations().get("testMethod"));

    }
}
