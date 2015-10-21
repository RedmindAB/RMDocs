package test.java.se.redmind;

import static org.junit.Assert.*;

import org.junit.Test;

import se.redmind.json.ProjectSerializer;
import se.redmind.structure.Project;
import se.redmind.util.StringCustomizer;

public class ProjectSerializerTest {

	ProjectSerializer ps = new ProjectSerializer();
	
	@Test
	public void assertTheCorrectLengthOfArrayWhenCreated() {
		String[] strArr = StringCustomizer.splitStringToArray("Step: [step] gå på toa [expected] går på toan [step] ha det bra [expected] trevligt");
		assertEquals(9, strArr.length);
	}
	
}
