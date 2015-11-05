package se.redmind.unit;

import org.junit.BeforeClass;
import org.junit.Test;
import se.redmind.file.FileFinder;
import se.redmind.file.RMFileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Victor Mattsson on 2015-10-23.
 */
public class RMFileReaderTest{


    RMFileReader reader = new RMFileReader();
    static FileFinder ff = new FileFinder(".java");

    @BeforeClass
    public static void before(){
        String str = System.getProperty("user.dir") + "/TestProject";
        ff.pathWalker(new File(str));
    }

    @Test
    public void testReturnedFileList(){
        assertEquals(ArrayList.class, reader.readAndSeparateFiles(ff.getFileList(), "@rm").getClass());

        assertEquals(8, reader.readAndSeparateFiles(ff.getFileList(), "@rm").size());
    }

    @Test
    public void ifFileListIsEmptyReturnEmptyList(){
        assertTrue(reader.readAndSeparateFiles(new ArrayList<File>(), "@rm").isEmpty());
    }

}