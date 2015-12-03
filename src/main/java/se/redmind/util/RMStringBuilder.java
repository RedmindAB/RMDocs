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

    public String toTextFileString(Project project) {

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

    public String toConfluenceTextString(Project project) {

        sb = new StringBuilder();

        sb.append("h1.").append(project.getProjectName()).append("\n");
        for (ClassObject co : project.getClassObjects()) {
            sb.append("h2.").append(co.getPackageName()).append("\n");
            sb.append("\n");
            sb.append("h3.").append(co.getName()).append("\n");
            sb.append("\n");

            for (Method m : co.getMethodList()) {
                sb.append("||").append(m.getMethodName()).append("|| ||").append("\n");
                for (String s : m.getCommentList()) {
                    String[] rmArray = s.split(":");
                    if (rmArray.length > 1) {
                        sb.append("|").append(rmArray[0]).append("|").append(rmArray[1]).append("|").append("\n");
                    } else {
                        sb.append("|").append(rmArray[0]).append("|");
                    }
                }
                for (Map.Entry<String, List<String>> entry : m.getDuplicateMap().entrySet()) {
                    String entryKey = entry.getKey();
                    sb.append("|*").append(entryKey).append(":*| |").append("\n");
                    for (String dup : entry.getValue()) {
                        String[] dupArray = StringCustomizer.splitStringToArray(dup);
                        if (dupArray.length == 3) {
                            sb.append("||").append(dupArray[1]).append("||").append(" ").append("||").append("\n");
                            sb.append("|").append(dupArray[2]).append("|").append(" ").append("|").append("\n");
                        } else if (dupArray.length == 1 || dupArray.length < 4) {
                            String[] splitArray = dup.split(":");
                            sb.append("||").append(dupArray[1]).append("||").append(" ").append("||").append("\n");
                            sb.append("|").append(splitArray[1]).append("|").append(" ").append("|").append("\n");
                        } else {
                            sb.append("||").append(dupArray[1]).append("||").append(dupArray[3]).append("||").append("\n");
                            String key = " ";
                            String value;
                            try {
                                key = dupArray[2];
                                value = dupArray[4].trim();
                            } catch (ArrayIndexOutOfBoundsException e) {
                                value = " ";
                            }
                            sb.append("|").append(key).append("|").append(value).append("|").append("\n");
                        }
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String toUncommentedReportString(List<String> unCommentedMethods) {
        sb = new StringBuilder();

        sb.append("--Methods with no comments--").append("\n");
        unCommentedMethods.forEach((method) -> sb.append(method).append("\n"));
        sb.append("\n");

        return sb.toString();
    }

    public String toMissingCommentReportString(LinkedHashMap<String, String> missingComments) {
        sb = new StringBuilder();

        sb.append("--Methods with missing annotations--").append("\n");

        for (Map.Entry<String, String> entry : missingComments.entrySet()) {
            sb.append(entry.getKey()).append(" is missing: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
