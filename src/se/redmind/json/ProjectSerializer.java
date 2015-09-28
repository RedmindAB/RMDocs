package se.redmind.json;

import java.lang.reflect.Type;

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

		jsonObject.addProperty("Project Name", proj.getProjectName()); // sets the project name

//		JsonElement jsonClasses = context.serialize(proj.getClassList());
//		jsonObject.add("Classes", jsonClasses);
		
		JsonArray ClassItems = new JsonArray(); // new json array that holds class items
		
		for(ClassObject co: proj.getClassList()){
			
			JsonObject jsonClass = new JsonObject(); // new json object as a class object
			
			ClassItems.add(jsonClass); // add class item to class item list
			
			jsonClass.addProperty("Class Name", co.getName()); // sets name and package name for this class item
			jsonClass.addProperty("Package Name", co.getPackName());
			
			
			JsonArray methodItems = new JsonArray(); // new json array for method items

			for (Method method : co.getMethodList()) {
				
				JsonObject jsonMethod = new JsonObject(); // new json object as a method item
				
				methodItems.add(jsonMethod); // add method item to method item list
				
				jsonMethod.addProperty("Method Name", method.getMethodName()); // sets name for this method item

				
				JsonArray duplicates = new JsonArray();
				
				for (String dup : method.getDuplicateList()) {
					
					JsonObject jsonDup = new JsonObject();
					duplicates.add(jsonDup);

					String[] itemArray = dup.split(":");
					
					jsonDup.addProperty(itemArray[0], itemArray[1].trim());
					
				}
			
//				JsonObject RMitem = new JsonObject();
				
				for (String item : method.getRmList()) {	
					
					String[] itemArray = item.split(":");
					
					jsonMethod.addProperty(itemArray[0], itemArray[1].trim());
//					RMItems.add(RMitem);
					
				}

				jsonMethod.add("Duplicates", duplicates); // add duplicates list to method item
				
			}
			jsonClass.add("Methods", methodItems);
		}
		jsonObject.add("classes", ClassItems);

		return jsonObject;
	}



}
