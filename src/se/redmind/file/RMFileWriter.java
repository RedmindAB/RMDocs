package se.redmind.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Writes a specific file to the given format that is declared
 * when the class is instantiated
 * @author Victor Mattsson
 */
public class RMFileWriter {

	private String format;
	public static final String TEXT = "text";
	public static final String HTML = "html";
	public static final String JSON = "json";

	public RMFileWriter(String format){
		this.format = format;
	}

	/**
	 * This method chooses method to write base on what format is given
	 * @param file The java file to be written. 
	 */
	public void printAndWrite(File file){

		switch(format){
		case "text": 
			writeToText(file);
			break;
		case "html": 
			writeToHTML();
			break;
		case "json": 
			writeToJson();
			break;
		}
	}

	private void writeToJson() {
		// TODO Auto-generated method stub

	}

	private void writeToHTML() {
		// TODO Auto-generated method stub

	}

	private void writeToText(File file) {

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
	 * Formats the line by replacing special signs and trims white space.
	 * @param line
	 * @return the formated line
	 */
	private String formatLine(String line){
		String formatedString = "";
		formatedString = line.replaceAll("[\\*\\/\\{]", "").trim();
		return formatedString;
	}
}
