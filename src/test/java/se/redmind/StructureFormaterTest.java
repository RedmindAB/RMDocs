package test.java.se.redmind;

import static org.junit.Assert.*;

import org.junit.Test;

import se.redmind.structure.StructureFormater;

public class StructureFormaterTest {

	StructureFormater sf = new StructureFormater("@rm");
	
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
}
