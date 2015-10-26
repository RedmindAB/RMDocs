package se.redmind.structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import se.redmind.util.StringCustomizer;

/**
 * Class that structures the input to a readable format
 * 
 * @author Victor Mattsson
 *
 */
public class StructureFormater {

    private List<ClassObject> coList = new ArrayList<>();
    private List<String> duplicateList;
    private LinkedHashMap<String, List<String>> duplicateMap;
    private List<String> unCommentedMethods = new ArrayList<>();
    private boolean duplicates = true;

    private String annotation;

    public StructureFormater(String anno) {
	annotation = anno;
    }

    /**
     * Reads given file and turns it into a StringBuilder
     * 
     * @param file
     * @return the StringBuilder object
     */
    public StringBuilder readFileToStringBuilder(File file) {

	StringBuilder sb = new StringBuilder();
	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    String currLine;

	    while ((currLine = br.readLine()) != null) {

		sb.append(currLine + "\n");
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return sb;
    }

    /**
     * Converts a StringBuilder object to an array of Strings
     * 
     * @param sb
     *            the StringBuilder to convert
     * @return the String array
     */
    public String[] toArray(StringBuilder sb) {

	String[] strArray = sb.toString().split("\\n");
	return strArray;
    }

    /**
     * Iterates an array of strings and creates POJOs of the project, class and
     * annotations
     * 
     * @param strArr
     */
    public void readArray(String[] strArr) {

	ClassObject co = new ClassObject();
	List<Method> methodList = new ArrayList<>();

	for (int y = 0; y < strArr.length; y++) {

	    // If a test method does not have any comments, add it to a separate
	    // list
	    if (isAMethod(strArr[y])) {

		if (isTestMethod(strArr, y)) {
		    String method = StringCustomizer.extractMethodName(strArr[y]);
		    String str = co.getPackName() + " -> " + co.getName() + " -> " + method;

		    unCommentedMethods.add(str);
		}
	    }
	    // If line contains an annotation
	    else if (containsAnnotation(strArr[y], annotation)) {

		y = createObject(strArr, methodList, y);
		continue;
	    }
	    // If line contains a class name
	    else if (containsClassName(strArr[y])) {
		co.setName(StringCustomizer.extractClassName(strArr[y]));
		continue;
	    }
	    // If line contains a package name
	    else if (containsPackageName(strArr[y])) {
		co.setPackName(StringCustomizer.extractPackageName(strArr[y]));
		continue;
	    }
	}
	co.setMethodList(methodList);
	coList.add(co);
    }

    private boolean isTestMethod(String[] strArr, int y) {
	boolean isTestMethod = false;
	for (int x = y; x > 0; x--) {
	    if (strArr[x].contains("@Test")) {
		isTestMethod = true;
		break;
	    }
	}
	return isTestMethod;
    }

    public boolean containsAnnotation(String str, String annotation) {
	if (str.contains(annotation)) {
	    return true;
	}
	return false;
    }

    public boolean containsPackageName(String str) {
	if (str.contains("package")) {
	    return true;
	}
	return false;
    }

    public boolean containsClassName(String str) {

	if (str.contains("public class"))
	    return true;
	else if (str.contains("public abstract class"))
	    return true;
	else if (str.contains("public final class"))
	    return true;
	else if (str.contains("private class"))
	    return true;

	return false;
    }

    /**
     * method that creates a new Method object for each method with annotations
     * 
     * @param strArr
     * @param methodList
     * @param y
     * @return
     */
    private int createObject(String[] strArr, List<Method> methodList, int y) {

	Method m = new Method();
	List<String> rmList = new ArrayList<>();
	duplicateMap = new LinkedHashMap<>();

	for (int i = y; i < strArr.length; i++) {
	    // TODO look at another option or modify this option to make sure
	    // that next line is a method and nothing else
	    if (strArr[i].contains("@Test")) {
		int x;
		for (x = i; x < strArr.length; x++) {
		    if (isAMethod(strArr[x])) {
			m.setMethodName(StringCustomizer.extractMethodName(strArr[x]));
			break;
		    } else if (containsAnnotation(strArr[x], annotation)) {
			addAnnotationToList(strArr[x], rmList);
		    }
		}

		m.setRmList(rmList);

		do {
		    checkForDuplicates(rmList);
		} while (duplicates);

		m.setDuplicateMap(duplicateMap);
		y = x;
		break;
	    } else if (containsAnnotation(strArr[i], annotation)) {
		addAnnotationToList(strArr[i], rmList);
	    }
	}

	methodList.add(m);
	return y;
    }

    private void addAnnotationToList(String str, List<String> rmList) {
	String extracted = StringCustomizer.extractAnnotationData(str);
	rmList.add(extracted);
    }

    public boolean isAMethod(String str) {

	if (str.contains("public void"))
	    return true;
	else if (str.contains("private void"))
	    return true;
	else if (str.contains("public final void"))
	    return true;
	else if (str.contains("private final void"))
	    return true;
	else if (str.contains("public static void"))
	    return true;
	else if (str.contains("private static void"))
	    return true;
	else if (str.contains("public static final void"))
	    return true;
	else if (str.contains("private static final void"))
	    return true;
	return false;
    }

    private void checkForDuplicates(List<String> rmList) {

	String duplicateString = "noValue";
	List<String> newList = new ArrayList<>();
	duplicateList = new ArrayList<>();

	// iterates the rmList and adds the "key" to the new list
	for (String str : rmList) {
	    try {
		newList.add(str.substring(0, str.indexOf(":")));
	    } catch (StringIndexOutOfBoundsException e) {
		newList.add("null");
	    }
	}

	// Find the duplicate string in the new list and set it to
	// duplicateString
	final Set<String> set1 = new HashSet<String>();

	for (String str : newList) {
	    if (!set1.add(str)) {
		duplicateString = str;
		duplicates = true;
		break;
	    }
	}

	if (duplicateString.equals("noValue")) {
	    duplicates = false;
	    return;
	}

	// if rmList contains the duplicate string, add the item in
	// duplicateList
	for (String str : rmList) {
	    if (str.startsWith(duplicateString)) {
		duplicateList.add(str);
	    }
	}
	duplicateMap.put(duplicateString, duplicateList);

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
     * 
     * @param file
     * @return the extracted and formated name
     */
    public String getProjectName(File file) {
	// TODO it currently checks for "src", may be needed to change it if for
	// example it's a C# project
	String formatedString = "";
	int nameCnt = file.toPath().getNameCount();
	int srcIndex = 0;

	for (int i = 0; i < nameCnt; i++) {
	    if (file.toPath().getName(i).toString().equals("src")
		    || file.toPath().getName(i).toString().equals("bin")) {
		srcIndex = i;
	    }
	}
	formatedString = file.toPath().getName(srcIndex - 1).toString();
	return formatedString;
    }

    public List<ClassObject> getClassList() {
	return coList;
    }

    public List<String> getUnCommentedMethods() {
	return unCommentedMethods;
    }

    public void setUnCommentedMethods(List<String> unCommentedMethods) {
	this.unCommentedMethods = unCommentedMethods;
    }

}
