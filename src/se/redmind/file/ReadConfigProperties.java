package se.redmind.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigProperties {

	String path = "";
	String propFileName = "config.properties";
	Properties prop;

	public String getPath() {
		return path;
	}
	public void setPropFileName(String propFileName) {
		this.propFileName = propFileName;
	}

	public String getPropValues(){

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);){
			prop = new Properties();
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				createConfigFile();
			}

			path = prop.getProperty("path");
			
		} catch (IOException e) {
			return null;
		} 
		return path;
	}
	
	public void createConfigFile() {
		
		prop = new Properties();
		File configFile = new File("./resources/config.properties");
		try (FileWriter writer = new FileWriter(configFile);){
			prop.setProperty("path", "");
			prop.store(writer, "RMDocs properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}