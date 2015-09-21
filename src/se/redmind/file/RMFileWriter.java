package se.redmind.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;


/**
 * Writes a specific file to the given format that is declared
 * when the class is instantiated
 * @author Victor Mattsson
 */
public class RMFileWriter {

	private String format;

	//	public static final String TEXT = "text";
	//	public static final String HTML = "html";
	//	public static final String JSON = "json";

	public RMFileWriter(String format){
		this.format = format;
	}

	/**
	 * This method chooses method to write base on what format is given
	 * @param file The java file to be written. 
	 */
	public void printAndWrite(Project proj){

		switch(format){
		case ".txt": 
			writeToText(proj);
			break;
		case ".html": 
			writeToHTML();
			break;
		case ".json": 
			//			writeToJson(file);
			break;
		default:
			System.err.println("Invalid output format: " + format);
			System.exit(1);
		}
	}
	private void writeToJson(File file) {
		new JsonWriter(file);
	}

	private void writeToHTML() {

	}
	
	/**
	 * Uses the generated project structure and prints it to a textfile in 
	 * a nesting for loop to retrieve all the data
	 * @param proj
	 */
	private void writeToText(Project proj) {

		try (PrintWriter writer = new PrintWriter("./Resources/lib/"+proj.getProjectName() + ".txt", "UTF-8");){

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
					writer.println();
				}
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
		}
	}
	
	/**
	 * Formats the line by replacing special signs and trims white space.
	 * @param line
	 * @return the formated line
	 */
//	private String formatLine(String line){
//		String formatedString = "";
//		formatedString = line.replaceAll("[\\*\\/\\{]", "").trim();
//		return formatedString;
//	}
}
