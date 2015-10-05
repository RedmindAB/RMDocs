package se.redmind.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCustomizer {

	public static String extractClassName(String str) {

		int begin = str.indexOf("class")+6;
		String className = str.substring(begin, str.indexOf("{"));
		if(className.contains(" ")){
			className = className.substring(0, className.indexOf(" "));
		}
		return className;
	}
	
	public static String extractPackageName(String str){
		
		return str.replaceFirst("package", "").replace(";", "").trim();
	}
	
	/**
	 * Formats the line by replacing special signs and trims white space.
	 * @param line
	 * @return the formated line
	 */
	public static String formatMethodLine(String line){
		String formatedString = "";
		formatedString = line.replaceAll("[\\/*\\*\\/\\{\\()]", "").trim();
		return formatedString;
	}
	
	/**
	 * Extract and format only the data we want for our objects
	 * @param element
	 * @return the extracted and formated data
	 */
	public static String extractAnnotationData(String element){

		Pattern pat = Pattern.compile("\\@rm(.*?)\\s");
		Matcher mat = pat.matcher(element);
		String newString = "";
		String secondString = "";
		String thirdString = "";
		String finalString = "";

		while (mat.find()) {
			newString = mat.group(1);
			secondString = element.replace(newString, "");
			thirdString = secondString.replaceAll("[\\*\\/]", "");
			finalString = newString + ": " + thirdString.replace("@rm", "").trim();
		}
		return finalString;
	}
}
