package se.redmind.file;

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
 * @author Victor Mattsson
 */
public class RMFileWriter {

	private String format;
	private String path;

	//	public static final String TEXT = "text";
	//	public static final String HTML = "html";
	//	public static final String JSON = "json";

	public RMFileWriter(String format, String path){
		this.format = format;
		this.path = path;
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

		try (PrintWriter writer = new PrintWriter(path+appendDateToFile(proj) + ".json", "UTF-8");){
			writer.write(js);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

		}
	}

	private void writeToHTML() {

	}

	/**
	 * Uses the generated project structure and prints it to a textfile in 
	 * a nesting for loop to retrieve all the data
	 * @param proj
	 */
	private void writeToText(Project proj) {

		try (PrintWriter writer = new PrintWriter(path + appendDateToFile(proj)+".txt", "UTF-8");){


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
					for(String dup: m.getDuplicateList()){
						writer.println(dup);
					}
					writer.println();
				}
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
		}
	}

	public String appendDateToFile(Project proj){

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String date = sd.format(new Date());

		String fileName = proj.getProjectName() +"-"+ date;

		return fileName;		
	}
}

