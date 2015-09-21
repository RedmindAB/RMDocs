package se.redmind.json;

public class CurrentFile {
	
	private String fileName;
	private Method methods[];
	
	public String getName() {
		return fileName;
	}
	public void setName(String name) {
		this.fileName = name;
	}
	public Method[] getMethods() {
		return methods;
	}
	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

}
