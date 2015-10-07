package se.redmind.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;

/**
 * Writes a specific file to the given format that is declared
 * when the class is instantiated
 *
 * @author Victor Mattsson
 */
public class RMFileWriter implements Runnable {

    private String format;
    private String path;
    private Project proj;

    public RMFileWriter(String format, String path, Project proj) {
        this.format = format;
        this.path = path;
        this.proj = proj;
    }

    /**
     * This method chooses method to write base on what format is given
     */
    public void printAndWrite() {

        switch (format) {
            case ".txt":
                writeToText(proj);
                break;
            case ".html":
                writeToHTML(proj);
                break;
            case ".json":
                writeToJson(proj);
                break;
            default:
                System.err.println("Invalid output format: " + format);
                System.exit(1);
        }
    }

    private void writeToJson(Project proj) {
        JsonWriter json = new JsonWriter(proj);
        String js = json.convertToJson();

        File txtDir = new File(path + "json");
        txtDir.mkdirs();

        write(js, new File(txtDir, appendDateToFile(proj) + ".json"));
    }

    private void writeToHTML(Project proj) {

        JsonWriter json = new JsonWriter(proj);
        String js = json.convertToJson();

        write(js, new File("./web/MyProject.json"));
    }

    private void write(String json, File pathAndFile) {
        try (PrintWriter writer = new PrintWriter(pathAndFile, "UTF-8");) {
            writer.write(json);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the generated project structure and prints it to a text file in
     * a nesting for loop to retrieve all the data
     *
     * @param proj
     */
    private void writeToText(Project proj) {

        File txtDir = new File(path + "txt");
        txtDir.mkdirs();

        try (PrintWriter writer = new PrintWriter(new File(txtDir, appendDateToFile(proj) + ".txt"), "UTF-8");) {

            for (ClassObject co : proj.getClassList()) {
                writer.println(co.getPackName());
                writer.println();
                writer.println(co.getName());
                writer.println();

                for (Method m : co.getMethodList()) {
                    writer.println("Method: " + m.getMethodName());
                    for (String s : m.getRmList()) {
                        writer.println(s);
                    }
                    for (String dup : m.getDuplicateList()) {
                        writer.println(dup);
                    }
                    writer.println();
                }
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String appendDateToFile(Project proj) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String date = sd.format(new Date());

        String fileName = proj.getProjectName() + "-" + date;

        return fileName;
    }

    @Override
    public void run() {
        printAndWrite();
    }
}

