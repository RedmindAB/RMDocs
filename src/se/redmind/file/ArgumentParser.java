package se.redmind.file;

import java.io.File;
import java.util.Arrays;

/**
 * ArgumentParser --- Sets the given in-arguments to local variables validates
 * and gives error messages if arguments are invalid
 * 
 * @author Victor Mattsson
 *
 */
public class ArgumentParser {

	private File path;
	private String annotation = "@rm";
	private String readFormat = ".java";
	private String[] arguments;
	private String outputFormat;
	private StringBuilder err = new StringBuilder();
	private final String[] validReadFormats = { ".java", ".txt", ".cs", ".js" };
	private final String[] validOutputFormats = { ".json", ".txt", ".html" };

	public String getValidReadFormats() {
		return Arrays.toString(validReadFormats);
	}

	public String getValidOutputFormats() {
		return Arrays.toString(validOutputFormats);
	}

	public File getPath() {
		return path;
	}

	public String getFileFormat() {
		return this.readFormat;
	}

	public StringBuilder getErr() {
		return err;
	}

	/**
	 * @param args
	 *            A String array containing the command line arguments
	 */
	public ArgumentParser(String[] args) {
		this.arguments = args;
	}

	public void parse() {
		for (int i = 0; i < arguments.length; i++) {
			argumentSeparator(i);
		}
		validatePath(path);
		validateAnnotation(annotation);
		validateReadFormat(readFormat);
		validateOutputFormat(outputFormat);

		if (err.length() > 0) {
			System.err.println(err);
			System.exit(1);
		}
	}

	/**
	 * Checks the in-arguments and search for flags and sets the local variables
	 * depending on the flag. If the flag combined with the argument is invalid
	 * an error message is printed and program shuts down
	 * 
	 * @param i
	 *            current iteration in the array
	 */
	private void argumentSeparator(int i) {
		switch (arguments[i]) {
		case "-p":
			if (arguments.length > i + 1) {
				path = new File(arguments[i + 1]);
			} else {
				err.append("No variable for path given\n");
			}
			break;
		case "-a":
			if (arguments.length > i + 1) {
				annotation = arguments[i + 1];
			} else {
				err.append("No annotation given\n");
			}
			break;
		case "-f":
			if (arguments.length > i + 1) {
				readFormat = arguments[i + 1];
			} else {
				err.append("No read format given\n");
			}
			break;
		case "-o":
			if (arguments.length > i + 1) {
				outputFormat = arguments[i + 1];
			} else {
				err.append("No output format given\n");
			}
			break;
		}
	}

	public String toString() {
		return "path: " + path.getAbsolutePath() + " Annotation: " + annotation + " Format to read: " + readFormat
				+ " Format to write: " + outputFormat;
	}

	/**
	 * Validates the File path
	 * 
	 * @param path
	 *            A File containing the command line argument for the path
	 */
	public void validatePath(File path) {
		if (path == null) {
			err.append("Please enter a path\n");
		} else if (!path.isDirectory()) {
			err.append("Path does not exist: " + path + "\n");
		}
	}

	/**
	 * Validates the annotation string
	 * 
	 * @param annotation2
	 *            A string containing command line argument for annotation
	 */

	public void validateAnnotation(String annotation2) {
		// TODO - validate annotations other than @rm
		if (annotation2 == null) {
			err.append("Invalid annotation: " + annotation2 + ", add \"-a annotation\" as argument." + "\n");
		} else if (!annotation2.equals("@rm")) {
			err.append("Invalid annotation: " + annotation2 + "\n");
		}
	}

	/**
	 * validates the format to be read
	 * 
	 * @param format
	 */
	public void validateReadFormat(String format) {
		if (format == null) {
			err.append("Invalid read format: " + format + "\n");
		} else if (!Arrays.asList(validReadFormats).contains(format)) {
			err.append("Invalid read format: [" + format + "]. Valid formats: " + getValidReadFormats() + "\n");
		}
	}

	public void validateOutputFormat(String outputFormat) {
		if (outputFormat == null) {
			err.append("Invalid output format: " + outputFormat + "\n");
		} else if (!Arrays.asList(validOutputFormats).contains(outputFormat)) {
			err.append("Invalid output format: [" + outputFormat + "]. Valid formats: " + getValidOutputFormats() + "\n");
		}
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public String getAnnotation() {
		return annotation;
	}
}