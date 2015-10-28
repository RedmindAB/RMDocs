package se.redmind.unit;

import org.junit.Test;
import se.redmind.structure.FormatterInit;
import se.redmind.structure.Project;
import se.redmind.structure.StructureFormatter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by victormattsson on 2015-10-23.
 */
public class FormatterInitTest {

    List<File> list = new ArrayList<>();
    Project proj = new Project();

    @Test
    public void returnedObjectisCorrect(){
        FormatterInit init = new FormatterInit(proj, new StructureFormatter("@rm"), list);
        proj.setProjectName("Project");

        assertEquals(StructureFormatter.class, init.format().getClass());

    }

}