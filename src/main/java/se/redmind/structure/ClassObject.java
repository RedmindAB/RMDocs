package se.redmind.structure;

import java.util.ArrayList;
import java.util.List;

public class ClassObject {

    private String className;
    private String packageName;
    private List<Method> methodList = new ArrayList<>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return className;
    }

    public void setName(String name) {
        this.className = name;
    }

    public List<Method> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<Method> methodList) {
        this.methodList = methodList;
    }

}
