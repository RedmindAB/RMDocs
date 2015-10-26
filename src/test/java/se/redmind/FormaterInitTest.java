package se.redmind;

import org.junit.Test;
import se.redmind.structure.FormaterInit;
import se.redmind.structure.Project;
import se.redmind.structure.StructureFormater;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by victormattsson on 2015-10-23.
 */
public class FormaterInitTest {

    List<File> list = new ArrayList<>();
    Project proj = new Project();

    @Test
    public void returnedObjectisCorrect(){
        FormaterInit init = new FormaterInit(proj, new StructureFormater("@rm"), list);
        proj.setProjectName("Project");

        assertEquals(StructureFormater.class, init.format().getClass());

    }

}