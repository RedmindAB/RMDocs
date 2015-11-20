package se.redmind.file;

import com.google.gson.JsonObject;
import se.redmind.json.JsonWriter;

import java.io.File;
import java.util.List;

/**
 * Created by Victor Mattsson on 2015-11-20.
 */
public class JsonToXLSFileConverter {

    private final JsonWriter jsonWriter;
    private final XLSWriter xlsWriter;

    public JsonToXLSFileConverter() {
        jsonWriter = new JsonWriter();
        xlsWriter = new XLSWriter();
    }

    public File getFilteredXLSFile(JsonObject json, List<String> filter){
        json = jsonWriter.filter(json, filter);
        return convertFile(json);
    }

    public File getXLSFile(JsonObject json){
        return convertFile(json);
    }

    private File convertFile(JsonObject json){
        return xlsWriter.getAsFile(xlsWriter.format(json));
    }
}
