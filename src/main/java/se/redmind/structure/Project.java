package se.redmind.structure;

import java.util.List;


public class Project {

    private String projectName;
    private List<ClassObject> classObjects;
    private List<String> unCommentedMethods;
    private List<String> searchAnnotation;

    public List<String> getSearchAnnotation() {
		return searchAnnotation;
	}

	public void setSearchAnnotation(List<String> searchAnnotation) {
		this.searchAnnotation = searchAnnotation;
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