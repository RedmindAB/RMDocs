package se.redmind.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;

public class JsonWriter {

	private Project proj;

	public JsonWriter(Project proj) {
		this.proj = proj;
	}

	public String convertToJson(){
		
		//This should probably be checked in previous steps but added here for unit-testing purpose
		if(proj == null){
			throw new NullPointerException("Project is null");
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		System.out.println(gson.toJson(proj));
		
		return gson.toJson(proj);
	}
}
