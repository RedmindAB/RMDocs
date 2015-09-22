package se.redmind.structure;

import java.util.List;

public class Method {

	private String methodName;
	private List<String> rmList;
	private List<String> duplicateList;

	public List<String> getRmList() {
		return rmList;
	}

	public void setRmList(List<String> rmList) {
		this.rmList = rmList;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setDuplicateList(List<String> duplicateList) {
		this.duplicateList = duplicateList;
	}

	public List<String> getDuplicateList() {
		return duplicateList;
	}
	
}
