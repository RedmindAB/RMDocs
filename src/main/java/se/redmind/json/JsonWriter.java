package se.redmind.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.redmind.structure.Project;

public class JsonWriter {

	private Project project;

	public JsonWriter(Project project) {
		this.project = project;
	}

	public String convertToJson(){
		
		if(project == null){
			return "Project is null";
		}

		if(project.getClassObjects() == null){
			return "";
		}

		GsonBuilder gBuilder = new GsonBuilder();
		gBuilder.registerTypeAdapter(Project.class, new ProjectSerializer());
		Gson gson = gBuilder.setPrettyPrinting().create();

        return gson.toJson(project);
	}
}
