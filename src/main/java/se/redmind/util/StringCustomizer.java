package se.redmind.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.redmind.structure.Project;

public class StringCustomizer {

    public static String extractClassName(String line) {

        int begin = line.indexOf("class") + 6;
        String className = line.substring(begin, line.indexOf("{"));
        if (className.contains(" ")) {
            className = className.substring(0, className.indexOf(" "));
        }
        return className;
    }

    public static String extractPackageName(String line) {

        return line.replaceFirst("package", "").replace(";", "").trim();
    }

    /**
     * Formats the line by replacing special signs and trims white space.
     *
     * @param line
     * @return the formated line
     */
    public static String extractMethodName(String line) {
        String formatedString = line.split("\\(")[0];
        String[] strArr = formatedString.split("\\s");
        return strArr[strArr.length - 1];
    }

    /**
     * Extract and format only the data we want for our objects
     *
     * @param element
     * @return the extracted and formatted data
     */
    public static String extractAnnotationData(String element, String annotation) {

        Pattern pat = Pattern.compile("@rm(.*?)\\s");
        Matcher mat = pat.matcher(element);
        String newString;
        String secondString;
        String thirdString;
        String finalString = "";

        while (mat.find()) {
            newString = mat.group(1);
            secondString = element.replace(newString, "");
            thirdString = secondString.replaceAll("[\\*\\/]", "");
            if (thirdString.equals("")) thirdString = " ";
            finalString = newString + ": " + thirdString.replace(annotation, "").trim();
        }
        return finalString;
    }

    public static String appendDateToFile(Project proj) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String date = sd.format(new Date());

        return proj.getProjectName() + "-" + date;
    }

    public static String[] splitStringToArray(String str) {
        return str.split("\\[|\\]");
    }

    public static String[] splitToArrayWithDelimiter(String str, String delimiter){
        return str.split(delimiter);
    }

}
