package se.redmind.json;

public class Package {

	private String packageName;
	private CurrentFile[] files;
	
	public String getName() {
		return packageName;
	}
	public void setName(String name) {
		this.packageName = name;
	}
	public CurrentFile[] getFiles() {
		return files;
	}
	public void setFiles(CurrentFile[] files) {
		this.files = files;
	}
}
