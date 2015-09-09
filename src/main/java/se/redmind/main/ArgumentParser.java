package se.redmind.main;

import java.io.File;

public class ArgumentParser {

	private File path;
	private String annotation;
	private final String FORMAT = " [-p path -a @annotation]";
	private String err = "Invalid format." + FORMAT;
	
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
	
	public String toString(){
		return "path: " + path.getAbsolutePath() + " Annotation: " + annotation;
	}
	
	private void validatePath(File path){
		if(!path.isDirectory()){
			System.err.println("Path does not exist: " + path.getAbsolutePath());
			System.exit(1);
		}
	}
	
	private void validateAnnotation(String annotation2) {

		if(!annotation2.equals("@rm")){
			System.err.println("Invalid annotation: " + annotation2);
			System.exit(1);
		}
	}
}
