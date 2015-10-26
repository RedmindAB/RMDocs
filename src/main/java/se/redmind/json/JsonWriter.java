package se.redmind.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.redmind.structure.Project;

public class JsonWriter {

	private Project proj;

	public JsonWriter(Project proj) {
		this.proj = proj;
	}

	public String convertToJson(){
		
		if(proj == null){
			return "Project is null";
		}

		if(proj.getClassList() == null){
			return "";
		}

		GsonBuilder gBuilder = new GsonBuilder();
		gBuilder.registerTypeAdapter(Project.class, new ProjectSerializer());
		Gson gson = gBuilder.setPrettyPrinting().create();
		String json = gson.toJson(proj);
		
		return json;
	}
}
