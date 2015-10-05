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
			throw new NullPointerException("Project is null");
		}
		
		GsonBuilder gBuilder = new GsonBuilder();
		
		gBuilder.registerTypeAdapter(Project.class, new ProjectSerializer());
		
		Gson gson = gBuilder.setPrettyPrinting().create();
		
		String json = gson.toJson(proj);
		
		System.out.println(json);
		
		return json;
	}
}
