package se.redmind.main;

import java.io.File;
import java.util.List;

import se.redmind.file.ArgumentParser;
import se.redmind.file.FileFinder;
import se.redmind.file.RMFileReader;
import se.redmind.file.RMFileWriter;
import se.redmind.file.ConfigProperties;
import se.redmind.structure.FormatterInit;
import se.redmind.structure.Project;
import se.redmind.structure.StructureFormatter;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Arguments needed: [-p, -o]");
            System.exit(0);
        }

        // Initializes and reads the config-file
        ConfigProperties properties = new ConfigProperties();

        // Section to read the files
        List<File> fileList;
        ArgumentParser arg = new ArgumentParser(args);
        arg.parse();
        FileFinder finder = new FileFinder(arg.getReadFormat());
        RMFileReader reader = new RMFileReader();
        finder.pathWalker(arg.getPath());
        fileList = reader.readAndSeparateFiles(finder.getFileList(), arg.getAnnotation());

        // Section to structure the file list to POJO
        StructureFormatter formatter = new StructureFormatter(arg.getAnnotation(), arg.getSearchString());
        Project project = new Project();
        formatter = new FormatterInit(project, formatter, fileList).format();
        project.setClassObjects(formatter.getClassList());
        project.setUnCommentedMethods(formatter.getUnCommentedMethods());
        project.setSearchAnnotation(formatter.getSearchAnnotation());

        // Section to write the POJOs to specified format
        for (String format : arg.getOutFormats()) {
            new Thread(new RMFileWriter(format, properties.getPath(), project)).start();
        }

        new RMFileWriter(properties.getPath(), project).writeReport(project.getUnCommentedMethods(), project.getSearchAnnotation());
       
    }

}
