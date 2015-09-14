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
 *
 */

public class JavaFileReader {

	private List<File> annotatedFiles = new ArrayList<>();

	public void printList(){
		System.out.println("***List of java files containing @rm***");
		for(File f: annotatedFiles){
			System.out.println(f.getName());
		}
		System.out.println();
	}

	/**
	 * This method creates a text file based on the given filename
	 * @param file The java file to be read. 
	 */
	private void printAndWrite(File file){

		System.out.println("\n**Class**\n" + file.getName().replace(".java", ""));
		String currLine;
		
		try (BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter writer = new PrintWriter("./Resources/lib/"+file.getName().replace(".java", "") + ".txt", "UTF-8");){
			
			while((currLine = br.readLine()) != null){
				writeLine(br, writer, currLine);
			}
		} catch (IOException e1){
			e1.printStackTrace();
		}
	}

	/**
	 * searches for the annotations in the java file and writes them to a text file 
	 * @param writer
	 * @param currLine
	 * @throws IOException
	 */
	private void writeLine(BufferedReader br, PrintWriter writer, String currLine) throws IOException {
		if(currLine.contains("@rm")){
			System.out.println(formatLine(currLine));
			writer.println(formatLine(currLine));
		}			
		else if(currLine.contains("@Test")){ 
			String newLine = br.readLine();
			System.out.println(formatLine(newLine) + "\n");
			writer.println(formatLine(newLine) + "\n");
		}
	}

	/**
	 * Formats the line
	 * @param line
	 * @return the formated line
	 */
	private String formatLine(String line){
		String formatedString = "";
		formatedString = line.replaceAll("[\\*\\/\\{]", "").trim();
		return formatedString;
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
						printAndWrite(file);
						break;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		printList();
	}
}
