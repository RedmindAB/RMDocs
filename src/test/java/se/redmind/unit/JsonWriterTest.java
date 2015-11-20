package se.redmind.unit;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;

import java.io.File;
import java.util.*;

public class JsonWriterTest {

    JsonWriter jw;
    Project proj = new Project();

    @Before
    public void before() {
        jw = new JsonWriter();
    }

    @Test
    public void ifprojectIsNullReturnCorrectString() {
        proj = null;
        assertEquals("Project is null", jw.convertToJson(proj));
    }

    @Test
    public void ifClassListIsNullReturnEmptyString() {
        jw = new JsonWriter();
        assertEquals("", jw.convertToJson(proj));
    }

    @Test
    public void returnedValueIsAString() {
        List<ClassObject> list = new ArrayList<>();
        list.add(new ClassObject());

        jw = new JsonWriter();
        proj.setProjectName("Project");
        proj.setClassObjects(list);
        assertEquals(String.class, jw.convertToJson(proj).getClass());
    }

    @Test
    public void testFilter() {

        JsonObject obj = JsonGenerator.generate(1, 5);
        assertEquals("TestProject", obj.getAsJsonObject().get("ProjectName").getAsString());

        obj = JsonGenerator.generate(2, 5);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(obj));
    }

}
