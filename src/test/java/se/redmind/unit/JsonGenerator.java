package se.redmind.unit;

import com.google.gson.JsonObject;
import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Victor Mattsson on 2015-11-20.
 */
public class JsonGenerator {

    public static JsonObject generate(int classQuantity, int methodQuantity){
        Project project = new Project();
        JsonObject obj = new JsonObject();
        JsonWriter writer = new JsonWriter();
        List<ClassObject> classes = new ArrayList<>();
        List<Method> methods;

        project.setProjectName("TestProject");

        for (int i = 1; i <= classQuantity; i++){
            methods = new ArrayList<>();

            for (int y = 1; y <= methodQuantity; y++){
                Method method = getMethodObject("method" + y, new ArrayList<>(), new LinkedHashMap<>());
                methods.add(method);
            }

            ClassObject classObj = getClassObject("Class" + i, "se.redmind.tests.package", methods);
            classes.add(classObj);
        }
        project.setClassObjects(classes);
        obj = writer.convertToJsonObject(project);
        return obj;
    }

    public static ClassObject getClassObject(String name, String packName, List<Method> methods){
        ClassObject co = new ClassObject();
        co.setName(name);
        co.setPackageName(packName);
        co.setMethodList(methods);
        return co;
    }

    public static Method getMethodObject(String name, List<String> comments, LinkedHashMap<String, List<String>> duplicates){
        Method method = new Method();
        method.setMethodName(name);
        method.setCommentList(comments);
        method.setDuplicateMap(duplicates);
        return method;
    }
}
