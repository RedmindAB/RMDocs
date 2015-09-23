package se.redmind.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigProperties {

	String path = "";
	String propFileName = "config.properties";

	public String getPath() {
		return path;
	}
	public void setPropFileName(String propFileName) {
		this.propFileName = propFileName;
	}

	public String getPropValues(){

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);){
			Properties prop = new Properties();
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			path = prop.getProperty("path");
			
		} catch (IOException e) {
			System.out.println("Exception: " + e);
		} 
		return path;
	}
}