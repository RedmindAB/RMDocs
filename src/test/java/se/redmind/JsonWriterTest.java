package test.java.se.redmind;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.redmind.json.JsonWriter;
import se.redmind.structure.Project;

public class JsonWriterTest {

	JsonWriter jw;
	Project proj = new Project();
	
	@Before
	public void before(){
		jw = new JsonWriter(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void ifprojectIsNullThrowNullPointerException() {
		jw.convertToJson();
	}
	
	@Test
	public void ifProjectIsEmptyJsonIsEmpty(){
		jw = new JsonWriter(proj);
		assertEquals("{\n  \"projectName\": \"\"\n}", jw.convertToJson());
	}
	
	@Test
	public void checkIfProjectNameIsCorrect(){
		proj.setProjectName("MittProjekt");
		jw = new JsonWriter(proj);
		assertEquals("{\n  \"projectName\": \"MittProjekt\"\n}", jw.convertToJson());
	}

}
