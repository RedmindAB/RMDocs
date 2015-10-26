package se.redmind.structure;

import java.util.List;



public class Project {

	private String projectName;
	private List<ClassObject> classList;
	private List<String> unCommentedMethods;

	public List<String> getUnCommentedMethods() {
		return unCommentedMethods;
	}

	public void setUnCommentedMethods(List<String> unCommentedMethods) {
		this.unCommentedMethods = unCommentedMethods;
	}

	public List<ClassObject> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassObject> classList) {
		this.classList = classList;
	}

	public Project(){
		projectName = "";
	}

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


}