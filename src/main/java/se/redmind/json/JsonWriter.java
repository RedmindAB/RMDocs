package se.redmind.json;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.gson.*;

import org.apache.commons.io.FileUtils;

import se.redmind.structure.Project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonWriter {

    public String convertToJson(Project project) {

        if (project == null) {
            return "Project is null";
        }

        if (project.getClassObjects() == null) {
            return "";
        }

        GsonBuilder gBuilder = new GsonBuilder();
        Gson gson = gBuilder.setPrettyPrinting().create();
        return gson.toJson(convertToJsonElement(project));
    }

    public JsonElement convertToJsonElement(Project project) {

        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(Project.class, new ProjectSerializer());
        Gson gson = gBuilder.create();

        return gson.toJsonTree(project);
    }

    public JsonObject convertToJsonObject(Project project) {
        return convertToJsonElement(project).getAsJsonObject();
    }

    public JsonObject filter(JsonObject obj, List<String> filterLines) {
        return filter(obj, fromLines(filterLines));
    }

    public JsonObject filter(JsonObject obj, File filterFile) {
        return filter(obj, fromFile(filterFile));
    }

    public JsonObject filter(JsonObject obj, Map<String, Map<String, List<MethodDescription>>> packageMap) {
        JsonObject output = new JsonObject();
        output.addProperty("ProjectName", obj.get("ProjectName").getAsString());
        JsonArray outputClasses = new JsonArray();
        output.add("Classes", outputClasses);

        for (JsonElement classes : obj.get("Classes").getAsJsonArray()) {
            String jsonPackageName = classes.getAsJsonObject().get("PackageName").getAsString();
            if (packageMap.containsKey(jsonPackageName)) {
                Map<String, List<MethodDescription>> classMap = packageMap.get(jsonPackageName);
                String className = classes.getAsJsonObject().get("ClassName").getAsString();
                if (classMap.containsKey(className)) {
                    List<MethodDescription> methods = classMap.get(className);
                    JsonObject outputClass = new JsonObject();
                    outputClass.addProperty("ClassName", className);
                    outputClass.addProperty("PackageName", jsonPackageName);
                    JsonArray outputMethods = new JsonArray();
                    outputClass.add("Methods", outputMethods);
                    outputClasses.add(outputClass);

                    JsonArray methodArray = classes.getAsJsonObject().getAsJsonArray("Methods");
                    for (JsonElement method : methodArray) {
                        String jsonMethodName = method.getAsJsonObject().get("MethodName").getAsString();
                        for (MethodDescription methodDescription : methods) {
                            if (methodDescription.name.equals(jsonMethodName)) {
                                outputMethods.add(method);
                            }
                            if (!methodDescription.browsers.isEmpty()) {

                                String browsers = "";
                                for (String b : methodDescription.browsers) {
                                    browsers += b + " ";
                                }
                                output.addProperty(methodDescription.name, browsers.trim());
                            }
                        }
                    }
                }
            }
        }
        return output;
    }

    private static final Pattern PATTERN = Pattern.compile("(.*)\\.(.*)#(\\w+)(?: (.*))?");

    public static Map<String, Map<String, List<MethodDescription>>> fromLines(List<String> lines) {

        Map<String, Map<String, List<MethodDescription>>> packageMap = new LinkedHashMap<>();
        for (String line : lines) {
            Matcher matcher = PATTERN.matcher(line);

            if (matcher.matches()) {
                String packageName = matcher.group(1);
                Map<String, List<MethodDescription>> classMap = packageMap.get(packageName);

                if (classMap == null) {
                    classMap = new LinkedHashMap<>();
                    packageMap.put(packageName, classMap);
                }
                String className = matcher.group(2);
                List<MethodDescription> methods = classMap.get(className);

                if (methods == null) {
                    methods = new ArrayList<>();
                    classMap.put(className, methods);
                }
                methods.add(new MethodDescription(matcher.group(3), matcher.group(4)));

            } else {
                throw new IllegalArgumentException("'" + line + "' doesn't match the expected format");
            }
        }
        return packageMap;
    }

    public static Map<String, Map<String, List<MethodDescription>>> fromFile(File file) {
        try {
            return fromLines(FileUtils.readLines(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class MethodDescription {

        public final String name;
        public final List<String> browsers;

        public MethodDescription(String name, String browsers) {
            this.name = name;
            if (!Strings.isNullOrEmpty(browsers)) {
                this.browsers = Splitter.on(" ").trimResults().splitToList(browsers);
            } else {
                this.browsers = new ArrayList<>();
            }
        }
    }

}
