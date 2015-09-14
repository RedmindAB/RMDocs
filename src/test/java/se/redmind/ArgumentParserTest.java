package test.java.se.redmind;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.redmind.file.ArgumentParser;
import se.redmind.main.Main;

public class ArgumentParserTest {
	
	ArgumentParser arg;
	
	@Before
	public void before(){
		String[] args = {"-p", "/home/victor", "-a", "@rm"};
		arg = new ArgumentParser(args);
	}
	


	@Test
	public void testTheReturnedStringOfToStringMethod() {
	
		assertEquals("path: /home/victor Annotation: @rm", arg.toString());
	}
	

}
