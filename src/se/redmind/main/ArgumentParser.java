package se.redmind.main;

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
	private final String FORMAT = " [-p path -a @annotation]";
	private String err = "Invalid format." + FORMAT;
	
	/**
	 * Checks the in-arguments and search for flags and sets the local variables
	 * depending on the flag. If the flag combined with the argument is invalid
	 * an error message is printed and program shuts down
	 * @param args A String array containing the command line arguments
	 */
	public ArgumentParser(String[] args){
		for(int i = 0; i < args.length; i++){
			switch(args[i]){
			case "-p": 
				if(args.length > i+1){
					path = new File(args[i+1]);
				}else{
					System.err.println(err);
					System.exit(1);
				}
				break;
			case "-a": 
				if(args.length > i+1){
					annotation = args[i+1];
				}else{
					System.err.println(err);
					System.exit(1);
				}
				break;
			}
		}
		validatePath(path);
		validateAnnotation(annotation);
	}
	
	public File getPath() {
		return path;
	}
	
	public String toString(){
		return "path: " + path.getAbsolutePath() + " Annotation: " + annotation;
	}
	
	/**
	 * Validates the File path
	 * @param path A File containing the command line argument for the path
	 */
	private void validatePath(File path){
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
		if(!annotation2.equals("@rm")){
			System.err.println("Invalid annotation: " + annotation2);
			System.exit(1);
		}
	}
}
