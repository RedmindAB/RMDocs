package se.redmind.structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that structures the input to a readable format
 * @author victor
 *
 */
public class StructureFormater {

	List<ClassObject> coList = new ArrayList<>();

	/**
	 * Reads given file and turns it into a StringBuilder
	 * @param file 
	 * @return the StringBuilder object
	 */
	public StringBuilder readFileToStringBuilder(File file){

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String currLine;

			while((currLine = br.readLine()) != null){

				sb.append(currLine+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return sb;
	}

	/**
	 * Converts a StringBuilder object to an array of Strings
	 * @param sb the StringBuilder to convert
	 * @return the String array
	 */
	public String[] toArray(StringBuilder sb){

		String[] strArray = sb.toString().split("\\n");
		return strArray;
	}

	/*
	 * Metod/klass som tar arrayen med strings och skapar objekt(?), som i JsonWriter 
	 */
	/**
	 * Iterates an array of strings and creates POJOs of the
	 * project, class and annotations
	 * @param strArr
	 */
	public void readArray(String[] strArr){

		ClassObject co = new ClassObject();
		List<Method> methodList = new ArrayList<>();

		for (int y = 0; y < strArr.length; y++) {

			if(strArr[y].contains("public class")){
				co.setName(strArr[y].replace("public class", "").replace("{", "").trim());
			}
			else if(strArr[y].contains("package")){
				String packName = strArr[y].replaceFirst("package", "").replace(";", "").trim();
				co.setPackName(packName);
			}
			else if(strArr[y].contains("@rm")){

				y = createObject(strArr, methodList, y);
			}
		}

		co.setMethodList(methodList);
		coList.add(co);
	}

	/**
	 * method that creates a new Method object for each method with annotations
	 * @param strArr
	 * @param methodList
	 * @param y
	 * @return
	 */
	private int createObject(String[] strArr, List<Method> methodList, int y) {

		Method m = new Method();
		List<String> rmList = new ArrayList<>();

		for(int i = y; i < strArr.length; i++){

			if(strArr[i].contains("@Test")){
				m.setMethodName(formatLine(strArr[i+1]));
				m.setRmList(rmList);
				y = i;
				break;
			}else{
				if(strArr[i].contains("@rm")){
					String extracted = extractData(strArr[i]);
					rmList.add(extracted);
				}
			}
		}
		methodList.add(m);
		return y;
	}

	/**
	 * Extract and format only the data we want for our objects
	 * @param element
	 * @return the extracted and formated data
	 */
	private String extractData(String element){

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

	/**
	 * Formats the line by replacing special signs and trims white space.
	 * @param line
	 * @return the formated line
	 */
	private String formatLine(String line){
		String formatedString = "";
		formatedString = line.replaceAll("[\\/*\\*\\/\\{\\()]", "").trim();
		return formatedString;
	}

	/**
	 * Extracts the project name from the file path
	 * @param file
	 * @return the extracted and formated name
	 */
	public String getProjectName(File file) {
		//TODO it currently checks for "src", may be needed to change it if for example it's a C# project
		String formatedString = "";
		String filepath = file.getPath();
		String[] stringArray = filepath.split("\\/");
		for(int i = 0; i < stringArray.length; i++){
			if(stringArray[i].equals("src")){
				formatedString = stringArray[i-1];
			}
		}
		return formatedString;
	}

	public List<ClassObject> getClassList() {
		return coList;
	}

}
