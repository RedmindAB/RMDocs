package se.redmind.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map.Entry;

import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;
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

		try (PrintWriter writer = new PrintWriter(new File(confluenceDirectory, StringCustomizer.appendDateToFile(project) + ".txt"),
				"UTF-8")) {

			writer.println("h1." + project.getProjectName());
			for (ClassObject co : project.getClassObjects()) {
				writer.println("h2." + co.getPackageName());
				writer.println();
				writer.println("h3." + co.getName());
				writer.println();

				for (Method m : co.getMethodList()) {
					writer.println("||" + m.getMethodName() + "|| ||");
					for (String s : m.getCommentList()) {
						String[] rmArray = s.split(":");
						writer.println("|" + rmArray[0] + "|" + rmArray[1] + "|");
					}
					for (Entry<String, List<String>> entry : m.getDuplicateMap().entrySet()) {
						String entryKey = entry.getKey();
						writer.println("|*" + entryKey + ":*| |");
						for (String dup : entry.getValue()) {
							String[] dupArray = StringCustomizer.splitStringToArray(dup);
							if (dupArray.length == 3) {
								writer.println("||" + dupArray[1] + "||" + " " + "||");
								writer.println("|" + dupArray[2] + "|" + " " + "|");
							} else if (dupArray.length == 1 || dupArray.length < 4) {
								String[] splitArray = dup.split(":");
								writer.println("||" + dupArray[1] + "||" + " " + "||");
								writer.println("|" + splitArray[1] + "|" + " " + "|");
							} else {
								writer.println("||" + dupArray[1] + "||" + dupArray[3] + "||");
								String key = " ";
								String value;
								try {
									key = dupArray[2];
									value = dupArray[4].trim();
								} catch (ArrayIndexOutOfBoundsException e) {
									value = " ";
								}
								writer.println("|" + key + "|" + value + "|");
							}
						}
					}
					writer.println();
				}
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void writeToXLS() {

		XLSWriter xls = new XLSWriter(path, project);
		xls.write();
	}

	private void writeToJson() {
		jsonWriter = new JsonWriter(project);
		String jsonString = jsonWriter.convertToJson();

		File txtDirectory = new File(path + "json");
		txtDirectory.mkdirs();

		write(jsonString, new File(txtDirectory, StringCustomizer.appendDateToFile(project) + ".json"));
	}

	private void writeToHTML() {

		jsonWriter = new JsonWriter(project);
		String jsonString = jsonWriter.convertToJson();

		write(jsonString, new File("./web/MyProject.json"));

		SparkServer.start();
	}

	private void write(String json, File pathAndFile) {
		try (PrintWriter writer = new PrintWriter(pathAndFile, "UTF-8");) {
			writer.write(json);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uses the generated project structure and prints it to a text file in a
	 * nesting for loop to retrieve all the data
	 */
	private void writeToText() {

		File txtDirectory = new File(path + "txt");
		txtDirectory.mkdirs();

		try (PrintWriter writer = new PrintWriter(new File(txtDirectory, StringCustomizer.appendDateToFile(project) + ".txt"),
				"UTF-8")) {

			for (ClassObject co : project.getClassObjects()) {
				writer.println(co.getPackageName());
				writer.println();
				writer.println(co.getName());
				writer.println();

				for (Method m : co.getMethodList()) {
					writer.println("Method: " + m.getMethodName());
					for (String s : m.getCommentList()) {
						writer.println(s);
					}
					for (Entry<String, List<String>> entry : m.getDuplicateMap().entrySet()) {
						for (String dup : entry.getValue()) {
							writer.println(dup);
						}
					}
					writer.println();
				}
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		printAndWrite();
	}

	public void writeReport(List<String> list, List<String> missingComments) {

		try (PrintWriter writer = new PrintWriter(
				new File(path, StringCustomizer.appendDateToFile(project) + "-report.txt"), "UTF-8");) {

			if(!list.isEmpty()){
				writer.println("--Methods with no comments--");
				for (String method : list) {
					writer.println(method);
				}
				writer.println();
			}

			if(!missingComments.isEmpty()){
				writer.println("--Methods with missing annotation: "+ missingComments.get(0) +"--");

				for (String method : missingComments) {
					
					if (method == missingComments.get(0)) continue;
					writer.println(method);

				}
			}


		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}