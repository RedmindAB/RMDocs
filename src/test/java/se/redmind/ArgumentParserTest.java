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
		String[] args = {"-p", "/home/victor/redmind-workspace", "-a", "@rm", "-f", ".java", "-o", ".txt"};
		arg = new ArgumentParser(args);
		arg.parse();
	}
	


	@Test
	public void testTheReturnedStringOfToStringMethod() {
	
		assertEquals("path: /home/victor/redmind-workspace Annotation: @rm Format to read: "
				+ ".java Format to write: .txt", arg.toString());
	}
	

}
