package se.redmind.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFileReader --- This class reads and writes the given files and comments to text files.
 * @author Victor Mattsson , Özgür Eken
 */

public class RMFileReader {

	private List<File> annotatedFiles = new ArrayList<>();

	public List<File> getAnnotatedFiles() {
		return annotatedFiles;
	}

	/**
	 * This method searches for the annotation @rm in the file
	 * and adds it to a list.
	 * @param fileList List of files to be read.
	 */
	public void readFile(List<File> fileList){

		for(File file: fileList){

			try (BufferedReader br = new BufferedReader(new FileReader(file))){
				String currLine;
				
				while((currLine = br.readLine()) != null){
					if(currLine.contains("@rm")){
						annotatedFiles.add(file);
						break;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		printList();
	}
	
	public void printList(){
		System.out.println("***List of java files containing @rm***");
		for(File f: annotatedFiles){
			System.out.println(f.getName());
		}
		System.out.println();
	}
}
