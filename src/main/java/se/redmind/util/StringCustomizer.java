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
     * @param line - string to extract name from
     * @return the formatted line
     */
    public static String extractMethodName(String line) {
        String formattedString = line.split("\\(")[0];
        String[] strArr = formattedString.split("\\s");
        return strArr[strArr.length - 1];
    }

    /**
     * Extract and format only the data we want for our objects
     *
     * @param element - string to extract data from
     * @return the extracted and formatted data
     */
    public static String extractAnnotationData(String element, String annotation) {
        StringBuilder sb = new StringBuilder();
        Pattern Patter = Pattern.compile(".*" + annotation + "(\\w+)(?:\\s+(.+))?");
        Matcher mat = Patter.matcher(element.trim());

        if (mat.matches()) {
            if (mat.group(1) != null) {
                sb.append(mat.group(1)).append(": ");
            }
            if (mat.group(2) != null) {
                sb.append(mat.group(2));
            }
        }
        return sb.toString();
    }

    public static String appendDateToFile(Project proj) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String date = sd.format(new Date());

        return proj.getProjectName() + "-" + date;
    }

    public static String[] splitStringToArray(String str) {
        return str.split("\\[|\\]");
    }

}
