package se.redmind.unit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jxl.Sheet;
import jxl.Workbook;
import jxl.demo.ReadWrite;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;
import org.junit.Test;
import se.redmind.file.XLSWriter;
import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by victormattsson on 2015-10-23.
 */
public class XLSWriterTest {

    XLSWriter writer = new XLSWriter(System.getProperty("user.dir"));

    @Test
    public void testGetAsFile() throws IOException, BiffException {

        JsonWriter jw = new JsonWriter();
        JsonObject obj = JsonGenerator.generate(1, 2);

        WritableWorkbook workbook = writer.format(obj);
        File file = writer.getAsFile(workbook);

        Workbook w = Workbook.getWorkbook(file);
        assertEquals(1, w.getNumberOfSheets());
        Sheet sheet = w.getSheet(0);

        assertEquals(9, sheet.getRows());
        assertEquals(2, sheet.getColumns());

        assertEquals("se.redmind.tests.package", sheet.getCell(0, 0).getContents().toString());
        assertEquals("method1", sheet.getCell(0, 3).getContents().toString());
    }

}