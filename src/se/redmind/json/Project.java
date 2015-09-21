package se.redmind.json;

public class Project {

	private String projectName;
	private Package[] packages;
	
	public Package[] getPackages() {
		return packages;
	}
	public void setPackages(Package[] packages) {
		this.packages = packages;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
