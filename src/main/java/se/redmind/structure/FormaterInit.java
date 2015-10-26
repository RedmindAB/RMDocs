package se.redmind.structure;

import java.io.File;
import java.util.List;

public class FormaterInit {

	StringBuilder sb;
	String [] strArray;
	Project proj;
	StructureFormater formater;
	List<File> list;
	
	public FormaterInit(Project proj, StructureFormater formater, List<File> list) {
		this.proj = proj;
		this.formater = formater;
		this.list = list;
	}

	public StructureFormater format(){
		
		for(File file: list){
			if(proj.getProjectName().equals("")){
				proj.setProjectName(formater.getProjectName(file));
			}
			sb = formater.readFileToStringBuilder(file);
			strArray = formater.toArray(sb);
			formater.readArray(strArray);
		}
		return formater;
	}
}
