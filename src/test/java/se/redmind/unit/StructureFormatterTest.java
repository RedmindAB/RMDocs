package se.redmind.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.redmind.file.ArgumentParser;
import se.redmind.structure.StructureFormatter;
import se.redmind.util.Conditions;

import java.io.File;
import java.net.URISyntaxException;

public class StructureFormatterTest {

	StructureFormatter sf = new StructureFormatter("@rm");
	ArgumentParser arg;
    File file = new File(System.getProperty("user.dir") + "/src/test/java/se/redmind/TestFile.java");
    StringBuilder sb = new StringBuilder();

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
        assertEquals(StringBuilder.class, sf.readFileToStringBuilder(file).getClass());
    }

    @Test
    public void returnedObjectIsAStringArray(){
        assertEquals(String[].class, sf.toArray(sb).getClass());
    }

    @Test
    public void testIsATestMethod(){
//        assertTrue();
    }

}
