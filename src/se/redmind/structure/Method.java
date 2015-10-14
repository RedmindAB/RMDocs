package se.redmind.structure;

import java.util.List;
import java.util.TreeMap;

public class Method {

	private String methodName;
	private List<String> rmList;
	private TreeMap<String, List<String>> duplicateMap;

	public TreeMap<String, List<String>> getDuplicateMap() {
		return duplicateMap;
	}

	public void setDuplicateMap(TreeMap<String, List<String>> duplicateMap) {
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
