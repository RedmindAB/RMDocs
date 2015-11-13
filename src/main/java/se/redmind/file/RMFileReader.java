package se.redmind.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFileReader --- This class reads and writes the given files and comments
 * to text files.
 *
 * @author Victor Mattsson , Özgür Eken
 */

public class RMFileReader {

    private List<File> annotatedFiles = new ArrayList<>();

    public List<File> getAnnotatedFiles() {
        return annotatedFiles;
    }

    /**
     * This method searches for the annotation @rm in the file and adds it to a
     * list.
     *
     * @param fileList List of files to be read.
     */
    public List<File> readAndSeparateFiles(List<File> fileList, String annotation) {

        for (File file : fileList) {

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String currLine;

                while ((currLine = br.readLine()) != null) {
                    if (currLine.contains(annotation)) {
                        separateAnnotatedFiles(file);
                        break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getAnnotatedFiles();
    }

    /**
     * Method that separates the annotated files to a new list and at the same
     * time checks if the filename already exists in the list
     *
     * @param file - file to be added to separate list
     */
    private void separateAnnotatedFiles(File file) {

        if (annotatedFiles.isEmpty()) {
            annotatedFiles.add(file);
            return;
        }
        for (File annFile : annotatedFiles) {
            if (annFile.getName().equals(file.getName())) {
            } else {
                annotatedFiles.add(file);
                break;
            }
        }
    }
}
