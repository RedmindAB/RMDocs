package se.redmind.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	private StringBuilder err = new StringBuilder();
	private List<String> outputFormats = new ArrayList<>();
	private final String[] validReadFormats = { ".java", ".txt", ".cs", ".js" };
	private final String[] validOutputFormats = { ".json", ".txt", ".html", ".xls", ".con"};

	public String getValidReadFormats() {
		return Arrays.toString(validReadFormats);
	}

	public String getValidOutputFormats() {
		return Arrays.toString(validOutputFormats);
	}

	public File getPath() {
		return path;
	}

    public void setPath(File path) { this.path = path; }

	public String getReadFormat() {
		return this.readFormat;
	}

	public String getErr() {
		return err.toString();
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
			separateArguments(i);
		}
		validatePath(path);
		validateAnnotation(annotation);
		validateReadFormat(readFormat);
		validateOutputFormats(outputFormats);

        checkForErrors();
	}

	/**
	 * Checks the in-arguments and search for flags and sets the local variables
	 * depending on the flag. If the flag combined with the argument is invalid
	 * an error message is printed and program shuts down
	 *
	 * @param i
	 *            current iteration in the array
	 */
	private void separateArguments(int i) {
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
				if(!outputFormats.contains(arguments[i+1])){
					outputFormats.add(arguments[i+1]);
				}
			} else {
				err.append("No output format given\n");
			}
			break;
		}
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
			err.append("Path does not exist: ").append(path).append("\n");
		}
	}

	/**
	 * Validates the annotation string
	 *
	 * @param anno
	 *            A string containing command line argument for annotation
	 */

	public void validateAnnotation(String anno) {
		// TODO - validate annotations other than @rm
		if (anno == null) {
			err.append("Invalid annotation: " + "null" + ", add \"-a annotation\" as argument." + "\n");
		} else if (!anno.equals("@rm")) {
			err.append("Invalid annotation: ").append(anno).append("\n");
		}
	}

	/**
	 * validates the format to be read
	 *
	 * @param format
	 */
	public void validateReadFormat(String format) {
		if (format == null) {
			err.append("Invalid read format: ").append("null").append("\n");
		} else if (!Arrays.asList(validReadFormats).contains(format)) {
			err.append("Invalid read format: [").append(format).append("]. Valid formats: ").append(getValidReadFormats()).append("\n");
		}
	}

	public void validateOutputFormats(List<String> outputFormats) {
		if(outputFormats.isEmpty()){
			err.append("No output formats given.");
		}
		for (String format : outputFormats) {

			if (!Arrays.asList(validOutputFormats).contains(format)) {
				err.append("Invalid output format: [").append(format).append("]. Valid formats: ").append(getValidOutputFormats()).append("\n");
			}
		}
	}
	public void checkForErrors() {
        if (err.length() > 0) {
            System.err.println(err);
            System.exit(1);
        }
	}

	public List<String> getOutFormats() {
		return outputFormats;
	}

	public String getAnnotation() {
		return annotation;
	}
}