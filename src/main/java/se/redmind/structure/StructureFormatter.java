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

import org.apache.commons.lang3.StringUtils;
import se.redmind.util.Conditions;
import se.redmind.util.StringCustomizer;

/**
 * Structures the input to a readable format
 *
 * @author Victor Mattsson
 */
public class StructureFormatter {

    private List<ClassObject> classObjects = new ArrayList<>();
    private LinkedHashMap<String, List<String>> duplicateMap;
    private LinkedHashMap<String, String> methodsMissingAnnotations = new LinkedHashMap<>();

    private List<String> unCommentedMethods = new ArrayList<>();
    private boolean duplicates = true;

    private String[] searchString;
    private String annotation;

    public StructureFormatter(String anno) {
        annotation = anno;
    }

    public StructureFormatter(String anno, String[] searchString) {
        annotation = anno;
        this.searchString = searchString;
    }

    /**
     * Reads given file and turns it into a StringBuilder
     *
     * @param file the given file
     * @return the StringBuilder object
     */
    public StringBuilder readFileToStringBuilder(File file) {

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currLine;

            while ((currLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(currLine).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    /**
     * Converts a StringBuilder object to an array of Strings
     *
     * @param stringBuilder the StringBuilder to convert
     * @return the String array
     */
    public String[] toArray(StringBuilder stringBuilder) {

        return stringBuilder.toString().split("\\n");
    }

    /**
     * Iterates an array of strings and creates POJOs of the project, class and
     * annotations
     *
     * @param classStringArray array with each element as a line in a file
     */
    public void buildClassObjectFromArray(String[] classStringArray) {

        ClassObject classObject = new ClassObject();
        List<Method> methodList = new ArrayList<>();

        for (int y = 0; y < classStringArray.length; y++) {

            /* If a test method does not have any comments, add it to a separate list */
            if (Conditions.isAMethod(classStringArray[y])) {

                if (Conditions.isATestMethod(classStringArray, y)) {
                    String method = StringCustomizer.extractMethodName(classStringArray[y]);
                    String methodString = classObject.getPackageName() + " -> " + classObject.getName() + " -> " + method;

                    unCommentedMethods.add(methodString);
                }
            }
            /* If line contains an annotation */
            else if (Conditions.containsAnnotation(classStringArray[y], annotation)) {
                y = createObject(classStringArray, methodList, y);
            }
            /* If line contains a class name */
            else if (Conditions.containsClassName(classStringArray[y])) {
                classObject.setName(StringCustomizer.extractClassName(classStringArray[y]));
            }
            /* If line contains a package name */
            else if (Conditions.containsPackageName(classStringArray[y])) {
                classObject.setPackageName(StringCustomizer.extractPackageName(classStringArray[y]));
            }
        }
        classObject.setMethodList(methodList);
        classObjects.add(classObject);
    }

    /**
     * method that creates a new Method object for each method with annotations
     *
     * @param classStringArray - string array representing a class
     * @param methodList - list to add the Method objects
     * @param iteration - current iteration in parent method
     * @return current iteration when this method is done
     */
    private int createObject(String[] classStringArray, List<Method> methodList, int iteration) {

        Method method = new Method();
        List<String> comments = new ArrayList<>();
        duplicateMap = new LinkedHashMap<>();

        for (int i = iteration; i < classStringArray.length; i++) {
            // TODO look at another option or modify this option to make sure that next line is a method and nothing else
            if (classStringArray[i].contains("@Test")) {
                int x;
                for (x = i; x < classStringArray.length; x++) {
                    if (Conditions.isAMethod(classStringArray[x])) {
                        method.setMethodName(StringCustomizer.extractMethodName(classStringArray[x]));
                        break;
                    } else if (Conditions.containsAnnotation(classStringArray[x], annotation)) {
                        addAnnotationToList(classStringArray[x], comments);
                    }
                }

                if (searchString != null) {
                    checkForMissingComment(comments, method);
                }

                method.setCommentList(comments);

                do {
                    checkForDuplicates(comments);
                } while (duplicates);

                method.setDuplicateMap(duplicateMap);
                iteration = x;
                break;
            } else if (Conditions.containsAnnotation(classStringArray[i], annotation)) {
                addAnnotationToList(classStringArray[i], comments);
            }
        }

        methodList.add(method);
        return iteration;
    }


    private void checkForMissingComment(List<String> comments, Method method) {
        String methodName = method.getMethodName();
        for (String search : searchString) {
            boolean missing = true;
            for (String comment : comments) {
                String[] splitArray = StringCustomizer.splitToArrayWithDelimiter(comment, ":");
                if (splitArray[0].equalsIgnoreCase(search)) {
                    missing = false;
                    break;
                }
            }
            if (missing) {
                if(methodsMissingAnnotations.get(methodName) != null){
                    String oldVal = methodsMissingAnnotations.get(methodName);
                    if(!oldVal.equalsIgnoreCase(search))
                      methodsMissingAnnotations.replace(methodName, oldVal + ", " + StringUtils.capitalize(search));
                }else
                  methodsMissingAnnotations.put(methodName, StringUtils.capitalize(search));
            }
        }
    }

    private void addAnnotationToList(String comment, List<String> comments) {
        String extracted = StringCustomizer.extractAnnotationData(comment, annotation);
        comments.add(extracted);
    }

    private void checkForDuplicates(List<String> comments) {

        String duplicateString = "noValue";
        List<String> newCommentList = new ArrayList<>();
        List<String> duplicateList = new ArrayList<>();

        // iterates the rmList and adds the "key" to the new list
        for (String str : comments) {
            try {
                newCommentList.add(str.substring(0, str.indexOf(":")));
            } catch (StringIndexOutOfBoundsException e) {
                newCommentList.add("null");
            }
        }

        // Find the duplicate string in the new list and set it to duplicateString
        final Set<String> hashSet = new HashSet<>();

        for (String str : newCommentList) {
            if (!hashSet.add(str)) {
                duplicateString = str;
                duplicates = true;
                break;
            }
        }

        if (duplicateString.equals("noValue")) {
            duplicates = false;
            return;
        }

        // if rmList contains the duplicate string, add the item in duplicateList
        for (String str : comments) {
            if (str.startsWith(duplicateString)) {
                duplicateList.add(str);
            }
        }
        duplicateMap.put(duplicateString, duplicateList);

        for (String string : duplicateList) {
            for (Iterator<String> iterator = comments.iterator(); iterator.hasNext(); ) {
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
     * @param file - file to extract project name from
     * @return the extracted and formatted name
     */
    public String getProjectName(File file) {
        // TODO it currently checks for "src", may be needed to change it if for example it's a C# project
        String formattedString;
        int nameCount = file.toPath().getNameCount();
        int srcIndex = 0;

        for (int i = 0; i < nameCount; i++) {
            if (file.toPath().getName(i).toString().equals("src")
                    || file.toPath().getName(i).toString().equals("bin")) {
                srcIndex = i;
            }
        }
        formattedString = file.toPath().getName(srcIndex - 1).toString();
        return formattedString;
    }

    public List<ClassObject> getClassList() {
        return classObjects;
    }

    public List<String> getUnCommentedMethods() {
        return unCommentedMethods;
    }

    public LinkedHashMap<String, String> getMethodsMissingAnnotations() {
        return methodsMissingAnnotations;
    }

}
