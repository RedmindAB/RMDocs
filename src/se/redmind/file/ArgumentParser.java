package se.redmind.file;

import java.io.File;

/**
 * ArgumentParser --- Sets the given in-arguments to local variables
 * validates and gives error messages if arguments are invalid
 * @author Victor Mattsson
 *
 */
public class ArgumentParser {

	private File path;
	private String annotation;
	private String fileFormat;
	private String[] arguments;
	private String outputFormat;
	private final String FORMAT = " [-p path -a @annotation -f file-format]";
	private String err = "Invalid format." + FORMAT;
	
	/**
	 * @param args A String array containing the command line arguments
	 */
	public ArgumentParser(String[] args){
		this.arguments = args;
	}
	
	public void parse(){
		for(int i = 0; i < arguments.length; i++){
			argumentSeparator(i);
		}
		validatePath(path);
		validateAnnotation(annotation);
		validateReadFormat(fileFormat);
		validateOutputFormat(outputFormat);
	}

	/**
	 * Checks the in-arguments and search for flags and sets the local variables
	 * depending on the flag. If the flag combined with the argument is invalid
	 * an error message is printed and program shuts down
	 * @param i
	 */
	private void argumentSeparator(int i) {
		switch(arguments[i]){
		case "-p": 
			if(arguments.length > i+1){
				path = new File(arguments[i+1]);
			}else{
				System.err.println(err);
				System.exit(1);
			}
			break;
		case "-a": 
			if(arguments.length > i+1){
				annotation = arguments[i+1];
			}else{
				System.err.println(err);
				System.exit(1);
			}
			break;
		case "-f": 
			if(arguments.length > i+1){
				fileFormat = arguments[i+1];
			}else{
				System.err.println(err);
				System.exit(1);
			}
			break;
		case "-o": 
			if(arguments.length > i+1){
				outputFormat = arguments[i+1];
			}else{
				System.err.println(err);
				System.exit(1);
			}
			break;
		}
	}
	
	public File getPath() {
		return path;
	}
	
	public String getFileFormat() {
		return this.fileFormat;
	}
	
	public String toString(){
		return "path: " + path.getAbsolutePath() + " Annotation: " + annotation 
				+ " Format to read: " + fileFormat + " Format to write: " + outputFormat;
	}
	
	/**
	 * Validates the File path
	 * @param path A File containing the command line argument for the path
	 */
	private void validatePath(File path){
		if(path == null){
			System.err.println("Please enter a path");
			System.exit(1);
		}
		if(!path.isDirectory()){
			System.err.println("Path does not exist: " + path.getAbsolutePath());
			System.exit(1);
		}
	}
	
	/**
	 * Validates the annotation string
	 * @param annotation2 A string containing command line argument for annotation
	 */
	private void validateAnnotation(String annotation2) {
		// TODO - validate annotations other than @rm 
		if(annotation2 == null){ 
			System.err.println("Invalid annotation: " + annotation2 + ". Add [-a annotation] as argument");
			System.exit(1);
		}
		if(!annotation2.equals("@rm")){
			System.err.println("Invalid annotation: " + annotation2);
			System.exit(1);
		}
	}
	
	/**
	 * validates the format to be read
	 * @param format
	 */
	private void validateReadFormat(String format){
		if(format == null){
			System.err.println("Invalid read format: " + format);
			System.exit(1);
		}
	}
	private void validateOutputFormat(String outputFormat) {
		if(outputFormat == null){
			System.err.println("Invalid output format: " + outputFormat);
			System.exit(1);
		}
	}

	public String getOutputFormat() {
		return outputFormat;
	}
}

