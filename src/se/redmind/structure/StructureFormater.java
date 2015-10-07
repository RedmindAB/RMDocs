package se.redmind.structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import se.redmind.util.StringCustomizer;

/**
 * Class that structures the input to a readable format
 * @author victor
 *
 */
public class StructureFormater {

	List<ClassObject> coList = new ArrayList<>();
	List<String> duplicateList;

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

	/**
	 * Iterates an array of strings and creates POJOs of the
	 * project, class and annotations
	 * @param strArr
	 */
	public void readArray(String[] strArr, String anno){

		ClassObject co = new ClassObject();
		List<Method> methodList = new ArrayList<>();

		for (int y = 0; y < strArr.length; y++) {

			if(containsClassName(strArr[y])){
				co.setName(StringCustomizer.extractClassName(strArr[y]));
			}
				
			else if(containsPackageName(strArr[y])){
				co.setPackName(StringCustomizer.extractPackageName(strArr[y]));
			}
			else if(containsAnnotation(strArr[y], anno)){

				y = createObject(strArr, methodList, y);
			}
		}

		co.setMethodList(methodList);
		coList.add(co);
	}

	public boolean containsAnnotation(String str, String annotation){
		if(str.contains(annotation)){
			return true;
		}
		return false;
	}
	
	public boolean containsPackageName(String str){
		if(str.contains("package")){
			return true;
		}
		return false;
	}

	public boolean containsClassName(String str){
		
		if(str.contains("public class")) return true;
		else if(str.contains("public abstract")) return true;
		else if(str.contains("public final")) return true;
		else if(str.contains("private class")) return true;
		
		return false;
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
			//TODO look at another option or modify this option to make sure that next line is a method and nothing else
			if(strArr[i].contains("@Test")){
				int x;
				for(x = i; x < strArr.length; x++){
					if(isAMethod(strArr[x])){
						m.setMethodName(StringCustomizer.extractMethodName(strArr[x]));
						break;
					}
					lineContainsAnnotation(strArr[x], rmList);
				}
				
				m.setRmList(rmList);
				checkForDuplicates(rmList);
				m.setDuplicateList(duplicateList);
				y = x;
				break;
			}else{
				lineContainsAnnotation(strArr[i], rmList);
			}
		}

		methodList.add(m);
		return y;
	}

	private void lineContainsAnnotation(String str, List<String> rmList) {
		if(str.contains("@rm")){
			String extracted = StringCustomizer.extractAnnotationData(str);
			rmList.add(extracted);
		}
	}

	public boolean isAMethod(String str) {
		
		if(str.contains("public void")) return true;
		else if(str.contains("private void")) return true;
		else if(str.contains("public final void")) return true;
		else if(str.contains("private final void")) return true;
		else if(str.contains("public static void")) return true;
		else if(str.contains("private static void")) return true;
		else if(str.contains("public static final void")) return true;
		else if(str.contains("private static final void")) return true;
		return false;
	}

	private void checkForDuplicates(List<String> rmList) {

		String duplicateString  = "noValue";
		List<String> newList = new ArrayList<>();
		duplicateList = new ArrayList<>();

		for (String str : rmList) {
			try{
			newList.add(str.substring(0, str.indexOf(":")));
			}catch(StringIndexOutOfBoundsException e){
				newList.add("null");
			}
		}

		final Set<String> set1 = new HashSet<String>();

		for (String str : newList) {
			if (!set1.add(str)) {
				duplicateString = str;
			}
		}

		for (String str : rmList) {
			if(str.startsWith(duplicateString)){
				duplicateList.add(str);
			}
		}

		for (String string : duplicateList) {
			for (Iterator<String> iterator = rmList.iterator(); iterator.hasNext();) {
				String str = iterator.next();
				if (string.equals(str)) {
					iterator.remove();
				}
			}
		}

	}

	/** 
	 * Extracts the project name from the file path
	 * @param file
	 * @return the extracted and formated name
	 */
	public String getProjectName(File file) {
		//TODO it currently checks for "src", may be needed to change it if for example it's a C# project
		String formatedString = "";
		int nameCnt = file.toPath().getNameCount();
		int srcIndex = 0;

		for(int i = 0; i < nameCnt; i++){
			if(file.toPath().getName(i).toString().equals("src") || 
					file.toPath().getName(i).toString().equals("bin")){
				srcIndex = i;
			}
		}
		formatedString = file.toPath().getName(srcIndex-1).toString();
		return formatedString;
	}

	public List<ClassObject> getClassList() {
		return coList;
	}


}
