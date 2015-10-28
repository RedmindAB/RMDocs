package se.redmind.unit;

import static org.junit.Assert.*;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Test;

import se.redmind.json.ProjectSerializer;
import se.redmind.structure.Project;
import se.redmind.util.StringCustomizer;

public class ProjectSerializerTest {

	ProjectSerializer ps = new ProjectSerializer();
    GsonBuilder gBuilder = new GsonBuilder();
	
	@Test
	public void assertTheCorrectLengthOfArrayWhenCreated() {

	}
	
}
