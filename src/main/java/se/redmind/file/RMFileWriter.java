package se.redmind.file;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import se.redmind.json.JsonWriter;
import se.redmind.structure.Project;
import se.redmind.util.Configuration;
import se.redmind.util.RMStringBuilder;
import se.redmind.util.StringCustomizer;
import se.redmind.web.SparkServer;

/**
 * Handles the writing to the specific format
 *
 * @author Victor Mattsson
 */
public class RMFileWriter implements Runnable {

    private String format;
    private String path;
    private Project project;
    private JsonWriter jsonWriter;
    private RMStringBuilder builder = new RMStringBuilder();

    public RMFileWriter(String format, String path, Project project) {
        this.format = format;
        this.path = path;
        this.project = project;
    }

    public RMFileWriter(String path, Project project) {
        this.path = path;
        this.project = project;
    }

    /**
     * This method chooses method to write base on what format is given
     */
    public void printAndWrite() {

        switch (format) {
            case ".txt":
                writeToText();
                break;
            case ".html":
                writeToHTML();
                break;
            case ".json":
                writeToJson();
                break;
            case ".xls":
                writeToXLS();
                break;
            case ".con":
                writeToConfluence();
                break;
            default:
                System.err.println("Invalid output format: " + format);
                System.exit(1);
        }
    }

    private void writeToConfluence() {
        File confluenceDirectory = new File(path + "confluence");
        confluenceDirectory.mkdirs();

        File path = new File(confluenceDirectory.toString() + File.separator +
                StringCustomizer.appendDateToFile(project) + ".txt");

        try {
            FileUtils.writeStringToFile(path, builder.toConfluenceTextString(project));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private void writeToXLS() {
//
//        JsonWriter writer = new JsonWriter();
//        JsonObject obj = writer.convertToJsonObject(project);
//
//        XLSWriter xls = new XLSWriter(path);
//        xls.write(obj);
//    }

    private void writeToXLS() {

        JsonWriter writer = new JsonWriter();
        JsonObject obj = writer.convertToJsonObject(project);

        if(Configuration.getFilterBoolean()){
            obj = writer.filter(obj, Configuration.getFilterPath());
        }

        XLSWriter xls = new XLSWriter(path);
        xls.write(obj);
    }

    private void writeToJson() {
        String jsonString = new JsonWriter().convertToJson(project);

        File txtDirectory = new File(path + "json");
        txtDirectory.mkdirs();

        write(jsonString, new File(txtDirectory, StringCustomizer.appendDateToFile(project) + ".json"));
    }

    private void writeToHTML() {

        String jsonString = new JsonWriter().convertToJson(project);

        write(jsonString, new File("./web/MyProject.json"));

        SparkServer.start();
    }

    private void write(String json, File pathAndFile) {

        try {
            FileUtils.writeStringToFile(pathAndFile, json, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the generated project structure and prints it to a text file
     */
    public void writeToText() {

        File txtDirectory = new File(path + "txt");
        txtDirectory.mkdirs();

        File path = new File(txtDirectory.toString() + File.separator +
                StringCustomizer.appendDateToFile(project) + ".txt");

        try {
            FileUtils.writeStringToFile(path, builder.toTextFileString(project), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        printAndWrite();
    }

    public void writeReport(List<String> unCommentedMethods, LinkedHashMap<String, String> missingComments) {

        File reportDirectory = new File(path + "reports");
        reportDirectory.mkdirs();

        File path = new File(reportDirectory.toString() + File.separator +
                StringCustomizer.appendDateToFile(project) + "-report.txt");

        try {
            if (!unCommentedMethods.isEmpty()) {

                FileUtils.writeStringToFile(path, builder.toUncommentedReportString(unCommentedMethods), "UTF-8", true);
            }

            if (!missingComments.isEmpty()) {
                FileUtils.writeStringToFile(path, builder.toMissingCommentReportString(missingComments), "UTF-8", true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}