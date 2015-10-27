package se.redmind.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;
import se.redmind.util.StringCustomizer;

public class ProjectSerializer implements JsonSerializer<Project> {

    @Override
    public JsonElement serialize(Project project, Type type, JsonSerializationContext context) {
        JsonObject mainJsonObject = new JsonObject(); // creates the main json object
        mainJsonObject.addProperty("ProjectName", project.getProjectName()); // sets the project name
        JsonArray jsonArrayClass = new JsonArray(); // new json array that holds class items

        for (ClassObject classObject : project.getClassObjects()) {
            JsonObject jsonObjectClass = new JsonObject(); // new json object as a class object
            jsonArrayClass.add(jsonObjectClass); // add class item to class item list
            jsonObjectClass.addProperty("ClassName", classObject.getName()); // sets name and package name for this class item
            jsonObjectClass.addProperty("PackageName", classObject.getPackageName());
            JsonArray jsonArrayMethods = new JsonArray(); // new json array for method items

            for (Method method : classObject.getMethodList()) {
                JsonObject jsonObjectMethod = new JsonObject(); // new json object as a method item
                jsonArrayMethods.add(jsonObjectMethod); // add method item to method item list
                jsonObjectMethod.addProperty("MethodName", method.getMethodName()); // sets name for this method item


                if (method.getCommentList() != null) {
                    addPropertyToMethod(method, jsonObjectMethod);
                }

                if (!method.getDuplicateMap().isEmpty()) {

                    for (Entry<String, List<String>> entry : method.getDuplicateMap().entrySet()) {
                        JsonArray jsonArrayDuplicate = new JsonArray();
                        for (String duplicate : entry.getValue()) {
                            JsonObject jsonObjectDuplicate = new JsonObject();
                            String[] splitArray = StringCustomizer.splitStringToArray(duplicate);

                            if (splitArray.length == 1) {
                                String[] array = duplicate.split(":");
                                jsonObjectDuplicate.addProperty("key", array[1]);
                            } else {
                                for (int i = 1; i < splitArray.length; i += 2) {
                                    String key = splitArray[i];
                                    String value;
                                    try {
                                        value = splitArray[i + 1].trim();
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        value = "";
                                    }
                                    jsonObjectDuplicate.addProperty(key, value);
                                }
                            }
                            jsonArrayDuplicate.add(jsonObjectDuplicate);
                        }
                        jsonObjectMethod.add(entry.getKey(), jsonArrayDuplicate);
                    }
                }
            }
            jsonObjectClass.add("Methods", jsonArrayMethods);
        }

        mainJsonObject.add("Classes", jsonArrayClass);
        return mainJsonObject;
    }

    private void addPropertyToMethod(Method method, JsonObject jsonObjectMethod) {
        for (String item : method.getCommentList()) {
            if (item.equals("")) continue;
            String[] itemArray = item.split(":");
            jsonObjectMethod.addProperty(itemArray[0], itemArray[1].trim());
        }
    }
}
