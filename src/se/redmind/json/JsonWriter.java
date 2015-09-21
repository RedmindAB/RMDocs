package se.redmind.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter {

	private File file;

	public JsonWriter(File file) {
		this.file = file;
		readFile();
	}

	public void readFile(){
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String currLine;

			CurrentFile fileObject = new CurrentFile();
			fileObject.setName(file.getName());
			Method method = new Method();

			while((currLine = br.readLine()) != null){
				
				
				if(currLine.contains("@Test")){
					String newLine = br.readLine();
					method.setMethodName(newLine.replaceAll("[\\*\\/\\{\\()]", "").trim());
					continue;
				}
				else if(currLine.contains("@rmAuthor")){
					method.setAuthor(currLine.replaceAll("[\\@rmAuthor\\/*]", "").trim());
					continue;
				}
				else if(currLine.contains("@rmDate")){
					method.setDate(currLine.replaceAll("@rmDate", "").trim());
					continue;
				}
				else if(currLine.contains("@rmSummary")){
					method.setSummary(currLine.replaceAll("@rmSummary", "").trim());
					continue;
				}
				else if(currLine.contains("@rmDescription")){
					method.setDescription(currLine.replaceAll("@rmDescription", "").trim());
					continue;
				}
				else if(currLine.contains("@rmPriority")){
					method.setPriority(currLine.replaceAll("@rmPriority", "").trim());
					continue;
				}
			}
			
			fileObject.setMethods(new Method[]{method});
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			System.out.println(gson.toJson(fileObject));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
