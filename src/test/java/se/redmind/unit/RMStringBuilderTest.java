package se.redmind.unit;

import org.junit.Before;
import org.junit.Test;
import se.redmind.file.RMFileWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;
import se.redmind.util.RMStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Mattsson on 2015-11-04.
 */
public class RMStringBuilderTest {

    RMStringBuilder builder = new RMStringBuilder();
    Project proj;

    @Before
    public void before() {
        proj = getProject();
    }

    @Test
    public void testToTextFileString() throws Exception {
        String builtTextFileString = builder.toTextFileString(proj);
        assertNotNull(builtTextFileString);
        assertEquals(String.class, builtTextFileString.getClass());
        assertEquals(3675, builtTextFileString.length());
        assertTrue(builtTextFileString.startsWith("se.redmind.test"));
    }

    @Test
    public void testToConfluenceTextString() throws Exception {
        String builtConfluenceTextString = builder.toConfluenceTextString(proj);
        assertNotNull(builtConfluenceTextString);
        assertEquals(String.class, builtConfluenceTextString.getClass());
        assertEquals(5350, builtConfluenceTextString.length());
        assertTrue(builtConfluenceTextString.startsWith("h1.TestProject"));
    }

    @Test
    public void testToUncommentedReportString() throws Exception {
        String builtUnCommentedString = builder.toUncommentedReportString(Arrays.asList(
                "mockpackage1 -> MockTestClass1 -> createDatabase",
                "mockpackage1 -> MockTestClass1 -> createHTML"));
        assertNotNull(builtUnCommentedString);
        assertEquals(String.class, builtUnCommentedString.getClass());
        assertEquals(124, builtUnCommentedString.length());
        assertTrue(builtUnCommentedString.startsWith("--Methods with no comments--"));
    }

    @Test
    public void testToMissingCommentReportString() throws Exception {
        String builtMissingCommentString = builder.toMissingCommentReportString(new LinkedHashMap<String, String>() {{
            put("createHTML", "Step");
            put("createDatabse", "Gurkan");
        }});
        assertNotNull(builtMissingCommentString);
        assertEquals(String.class, builtMissingCommentString.getClass());
        assertEquals(98, builtMissingCommentString.length());
        assertTrue(builtMissingCommentString.startsWith("--Methods with missing annotations--"));
    }

    private Project getProject() {
        Project project = new Project();
        project.setProjectName("TestProject");

        for (int i = 1; i <= 4; i++) {
            List<ClassObject> coList = new ArrayList<>();

            for (int y = 1; y <= 5; y++) {
                ClassObject co = new ClassObject();
                List<Method> methodList = new ArrayList<>();

                co.setName("classObject" + y);
                co.setPackageName("se.redmind.test");
                for (int x = 1; x <= 5; x++) {
                    Method m = new Method();
                    m.setMethodName("methodObject" + x);
                    m.setCommentList(Arrays.asList("Author: Victor" + x, "Date: date" + x, "Summary: new summary" + x));
                    m.setDuplicateMap(new LinkedHashMap<String, List<String>>() {{
                                          put("Step", Arrays.asList("Step: [step] one"));
                                          put("Many", Arrays.asList("Many: [step] one", "Many: [expected] two"));
                                          put("Empty", Arrays.asList("Many: [step]"));
                                      }}
                    );
                    methodList.add(m);
                }
                co.setMethodList(methodList);

                coList.add(co);
            }
            project.setClassObjects(coList);
        }
        return project;
    }
}