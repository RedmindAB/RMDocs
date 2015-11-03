package se.redmind.structure;

import java.util.LinkedHashMap;
import java.util.List;


public class Project {

    private String projectName;
    private List<ClassObject> classObjects;
    private List<String> unCommentedMethods;
    private LinkedHashMap<String, String> methodsMissingAnnotation;

    public LinkedHashMap<String, String>getMethodsMissingAnnotation() {
		return methodsMissingAnnotation;
	}

	public void setMethodsMissingAnnotation(LinkedHashMap<String, String> methodsMissingAnnotation) {
		this.methodsMissingAnnotation = methodsMissingAnnotation;
	}

	public List<String> getUnCommentedMethods() {
        return unCommentedMethods;
    }

    public void setUnCommentedMethods(List<String> unCommentedMethods) {
        this.unCommentedMethods = unCommentedMethods;
    }

    public List<ClassObject> getClassObjects() {
        return classObjects;
    }

    public void setClassObjects(List<ClassObject> classObjects) {
        this.classObjects = classObjects;
    }

    public Project() {
        projectName = "";
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


}