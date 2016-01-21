package se.redmind.unit;

import com.google.gson.JsonObject;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import se.redmind.file.JsonToXLSFileConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Mattsson on 2015-11-20.
 */
public class JsonToXLSFileConverterTest {

    private JsonToXLSFileConverter converter = new JsonToXLSFileConverter();
    private List<String> list = new ArrayList<>();
    private JsonObject obj = JsonGenerator.generate(2, 5);

    @Test
    public void testGetFilteredXLSFile() throws Exception {

        for (int i = 1; i <= 5; i++){
            list.add("se.redmind.tests.package.Class1#method" + i);
        }

        File file = converter.getFilteredXLSFile(obj, list);
        assertEquals("tempfile.xls", file.getName());

        Workbook wb = Workbook.getWorkbook(file);
        Sheet sheet = wb.getSheet(0);
        assertTrue(file.isFile());
        assertEquals(2, sheet.getColumns());
        assertEquals(21, sheet.getRows());
    }

    @Test
    public void testGetXLSFile() throws Exception {
        File file = converter.getXLSFile(obj);
        assertEquals("tempfile.xls", file.getName());
        Workbook wb = Workbook.getWorkbook(file);
        Sheet sheet = wb.getSheet(0);
        assertTrue(file.isFile());
        assertEquals(2, sheet.getColumns());
        assertEquals(44, sheet.getRows());
    }
}