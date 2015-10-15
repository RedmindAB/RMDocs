package se.redmind.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map.Entry;
import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;
import se.redmind.util.StringCustomizer;

/**
 * Writes a specific file to the given format that is declared when the class is
 * instantiated
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

	public RMFileWriter(String path, Project proj) {
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
		case ".xls":
			writeToXLS(proj);
			break;
		default:
			System.err.println("Invalid output format: " + format);
			System.exit(1);
		}
	}

	private void writeToXLS(Project proj) {

		XLSWriter xsl = new XLSWriter(path, proj);
		xsl.write();
	}

	private void writeToJson(Project proj) {
		JsonWriter json = new JsonWriter(proj);
		String js = json.convertToJson();

		File txtDir = new File(path + "json");
		txtDir.mkdirs();

		write(js, new File(txtDir, StringCustomizer.appendDateToFile(proj) + ".json"));
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
	 * Uses the generated project structure and prints it to a text file in a
	 * nesting for loop to retrieve all the data
	 *
	 * @param proj
	 */
	private void writeToText(Project proj) {

		File txtDir = new File(path + "txt");
		txtDir.mkdirs();

		try (PrintWriter writer = new PrintWriter(new File(txtDir, StringCustomizer.appendDateToFile(proj) + ".txt"),
				"UTF-8");) {

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

	public void writeReport(List<String> list) {

		try (PrintWriter writer = new PrintWriter(
				new File(path, StringCustomizer.appendDateToFile(proj) + "-report.txt"), "UTF-8");) {

			writer.println("--Methods with no comments--");
			for (String method : list) {
				writer.println(method);
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
