package se.redmind.util;

import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor Mattsson on 2015-11-03.
 */
public class RMStringBuilder {

    private StringBuilder sb;

    public String toTextFileString(Project project){

        sb = new StringBuilder();

        for (ClassObject co : project.getClassObjects()) {
            sb.append(co.getPackageName()).append("\n");
            sb.append(co.getName()).append("\n");
            sb.append("\n");

            for (Method m : co.getMethodList()) {
                sb.append("Method: ").append(m.getMethodName()).append("\n");

                m.getCommentList().forEach((method) -> sb.append(method).append("\n"));

                for (Map.Entry<String, List<String>> entry : m.getDuplicateMap().entrySet()) {
                    entry.getValue().forEach((dup) -> sb.append(dup).append("\n"));
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String toConfluenceTextString(Project project){

        sb = new StringBuilder();

        sb.append("h1." + project.getProjectName()).append("\n");
        for (ClassObject co : project.getClassObjects()) {
            sb.append("h2." + co.getPackageName()).append("\n");
            sb.append("\n");
            sb.append("h3." + co.getName()).append("\n");
            sb.append("\n");

            for (Method m : co.getMethodList()) {
                sb.append("||" + m.getMethodName() + "|| ||").append("\n");
                for (String s : m.getCommentList()) {
                    String[] rmArray = s.split(":");
                    sb.append("|" + rmArray[0] + "|" + rmArray[1] + "|").append("\n");
                }
                for (Map.Entry<String, List<String>> entry : m.getDuplicateMap().entrySet()) {
                    String entryKey = entry.getKey();
                    sb.append("|*" + entryKey + ":*| |").append("\n");
                    for (String dup : entry.getValue()) {
                        String[] dupArray = StringCustomizer.splitStringToArray(dup);
                        if (dupArray.length == 3) {
                            sb.append("||" + dupArray[1] + "||" + " " + "||").append("\n");
                            sb.append("|" + dupArray[2] + "|" + " " + "|").append("\n");
                        } else if (dupArray.length == 1 || dupArray.length < 4) {
                            String[] splitArray = dup.split(":");
                            sb.append("||" + dupArray[1] + "||" + " " + "||").append("\n");
                            sb.append("|" + splitArray[1] + "|" + " " + "|").append("\n");
                        } else {
                            sb.append("||" + dupArray[1] + "||" + dupArray[3] + "||").append("\n");
                            String key = " ";
                            String value;
                            try {
                                key = dupArray[2];
                                value = dupArray[4].trim();
                            } catch (ArrayIndexOutOfBoundsException e) {
                                value = " ";
                            }
                            sb.append("|" + key + "|" + value + "|").append("\n");
                        }
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String toUncommentedReportString(List<String> unCommentedMethods){
        sb = new StringBuilder();

        sb.append("--Methods with no comments--").append("\n");
        unCommentedMethods.forEach((method) -> sb.append(method).append("\n"));
        sb.append("\n");

        return sb.toString();
    }

    public String toMissingCommentReportString(LinkedHashMap<String, String> missingComments){
        sb = new StringBuilder();

        sb.append("--Methods with missing annotations--").append("\n");

        for (Map.Entry<String, String> entry : missingComments.entrySet()) {
            sb.append(entry.getKey()).append(" is missing: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
