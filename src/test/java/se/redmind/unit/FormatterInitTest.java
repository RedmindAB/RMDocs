package se.redmind.unit;

import org.junit.Test;
import se.redmind.structure.FormatterInit;
import se.redmind.structure.Project;
import se.redmind.structure.StructureFormatter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Mattsson on 2015-10-23.
 */
public class FormatterInitTest {

    String projPath = System.getProperty("user.dir");
    List<File> list = Arrays.asList(
            new File(projPath + "/TestProject/Mock Project/Mock Project/src/se/redmind/mockpackage1/MockTestClass1.java"),
            new File(projPath + "/TestProject/Mock Project/Mock Project/src/se/redmind/mockpackage1/MockTestClass2.java"));
    Project proj = new Project();
    StructureFormatter formatter = new StructureFormatter("@rm");
    FormatterInit init;

    @Test
    public void returnedObjectisCorrect(){
        init = new FormatterInit(proj, formatter, list);
        proj.setProjectName("Project");

        assertEquals(StructureFormatter.class, init.format().getClass());

    }

    @Test
    public void ifProjectNameIsEmptySetItFromProjectPath(){
        init = new FormatterInit(proj, formatter, list);
        init.format();
        assertEquals("Mock Project", proj.getProjectName());
    }

}