package se.redmind;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Project;

import java.util.ArrayList;
import java.util.List;

public class JsonWriterTest {

	JsonWriter jw;
	Project proj = new Project();
	
	@Before
	public void before(){
		jw = new JsonWriter(null);
	}
	
	@Test
	public void ifprojectIsNullReturnCorrectString() {
        assertEquals("Project is null", jw.convertToJson());
	}
	
	@Test
	public void ifClassListIsNullReturnEmptyString(){
		jw = new JsonWriter(proj);
		assertEquals("", jw.convertToJson());
	}

    @Test
    public void returnedValueIsAString(){
        List<ClassObject> list = new ArrayList<>();
        list.add(new ClassObject());

        jw = new JsonWriter(proj);
        proj.setProjectName("Project");
        proj.setClassObjects(list);
        assertEquals(String.class, jw.convertToJson().getClass());
    }


}
