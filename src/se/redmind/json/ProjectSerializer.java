package se.redmind.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;

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

				if(method.getRmList() != null){
					for (String item : method.getRmList()) {
						if(item.equals("")) continue;
						String[] itemArray = item.split(":");
						jsonMethod.addProperty(itemArray[0], itemArray[1].trim());
					}
				}
				
				if(!method.getDuplicateMap().isEmpty()){
					
					for (Entry<String, List<String>> entry : method.getDuplicateMap().entrySet()){
						JsonArray dupArray = new JsonArray();
						for (String dup : entry.getValue()) {
							JsonObject jsonDup = new JsonObject();
							String[] itemArray = splitStringToArray(dup);

							for(int i = 1; i < itemArray.length; i+=2){
								String key = itemArray[i];
								String value = "";
								try{
									value = itemArray[i+1].trim();
								}catch(ArrayIndexOutOfBoundsException e){
									value = "";
								}
								jsonDup.addProperty(key, value);
							}
							dupArray.add(jsonDup);
						}
						jsonMethod.add(entry.getKey(), dupArray);
					}
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