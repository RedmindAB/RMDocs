package se.redmind.structure;

import java.util.LinkedHashMap;
import java.util.List;

public class Method {

	private String methodName;
	private List<String> rmList;
	private LinkedHashMap<String, List<String>> duplicateMap;

	public LinkedHashMap<String, List<String>> getDuplicateMap() {
		return duplicateMap;
	}

	public void setDuplicateMap(LinkedHashMap<String, List<String>> duplicateMap) {
		this.duplicateMap = duplicateMap;
	}

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

}
