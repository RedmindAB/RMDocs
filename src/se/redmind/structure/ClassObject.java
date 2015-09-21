package se.redmind.structure;

import java.util.ArrayList;
import java.util.List;

public class ClassObject {

	private String className;
	private String packName;
	private List<Method> methodList = new ArrayList<>();
	
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
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
