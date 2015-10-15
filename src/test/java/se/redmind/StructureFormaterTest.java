package test.java.se.redmind;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.redmind.file.ArgumentParser;
import se.redmind.structure.StructureFormater;

public class StructureFormaterTest {

	StructureFormater sf = new StructureFormater("@rm");
	ArgumentParser arg;
	
	@Before
	public void before() {
		String[] args = { "-p", "./src/test/java/se/redmind/Testfile.java", "-o", ".json" };
		arg = new ArgumentParser(args);
	}
	
	@Test
	public void ifLineIsAMethodReturnTrue(){
		assertTrue(sf.isAMethod("public void ahuhasudh(){"));
		assertTrue(sf.isAMethod("public static void kekeke() {"));
		assertTrue(sf.isAMethod("private void"));
		assertTrue(sf.isAMethod("private static void"));
		assertTrue(sf.isAMethod("public static final void"));
		assertTrue(sf.isAMethod("private static final void"));
	}
	
	@Test
	public void ifLineIsAMethodReturnTrueasdasd(){
		assertTrue(sf.isAMethod("public void"));

	}
	
	@Test
	public void handleMultipleFields(){
		arg.parse();
	}
}
