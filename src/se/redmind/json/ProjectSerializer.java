package se.redmind.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;
import se.redmind.unused.JsonMain;


public class ProjectSerializer implements JsonSerializer<Project>{

	@Override
	public JsonElement serialize(Project proj, Type type, JsonSerializationContext context) {

		JsonObject jsonObject = new JsonObject(); // creates the main json object

		jsonObject.addProperty("ProjectName", proj.getProjectName()); // sets the project name

		JsonArray ClassItems = new JsonArray(); // new json array that holds class items

		for(ClassObject co: proj.getClassList()){

			JsonObject jsonClass = new JsonObject(); // new json object as a class object

			ClassItems.add(jsonClass); // add class item to class item list

			jsonClass.addProperty("ClassName", co.getName()); // sets name and package name for this class item
			jsonClass.addProperty("PackageName", co.getPackName());


			JsonArray methodItems = new JsonArray(); // new json array for method items

			for (Method method : co.getMethodList()) {

				JsonObject jsonMethod = new JsonObject(); // new json object as a method item

				methodItems.add(jsonMethod); // add method item to method item list

				jsonMethod.addProperty("MethodName", method.getMethodName()); // sets name for this method item

				JsonArray duplicates = new JsonArray();

				if(method.getDuplicateList() != null){
					for (String dup : method.getDuplicateList()) {

						JsonObject jsonDup = new JsonObject();

						String[] itemArray = splitStringToArray(dup);

						for(int i = 1; i < itemArray.length; i+=2){
							jsonDup.addProperty(itemArray[i], itemArray[i+1].trim());
						}
						duplicates.add(jsonDup);
					}
				}

				if(method.getRmList() != null){
					for (String item : method.getRmList()) {	

						String[] itemArray = item.split(":");

						jsonMethod.addProperty(itemArray[0], itemArray[1].trim());
					}
				}

				if(duplicates.size() > 0){
					jsonMethod.add("Multiples", duplicates); // add duplicates list to method item
				}

			}
			jsonClass.add("Methods", methodItems);
		}
		jsonObject.add("Classes", ClassItems);

		return jsonObject;
	}


	public String[] splitStringToArray(String str){

		String[] strArr = str.split("\\[|\\]");

		return strArr;
	}

}
