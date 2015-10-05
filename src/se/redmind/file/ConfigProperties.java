package se.redmind.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigProperties --- Handles the config.properties file that sets initial properties
 * 
 * @author Victor Mattsson, Özgur Eken
 *
 */
public class ConfigProperties {

	private String path = "";
	private String propFileName = "config.properties";
	private Properties prop;

	public String getPath() {
		return this.path;
	}

	public void setPropFileName(String propFileName) {
		this.propFileName = propFileName;
	}

	public void setPathPropertyValue(String value) {
		prop = new Properties();
		prop.setProperty("path", value);
	}

	public String getPropValues() {

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);) {
			prop = new Properties();

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				createConfigFile();
			}

			path = prop.getProperty("path");
			if (path.equals("")) {
				prop.setProperty("path", setHomePath());
				path = System.getProperty("user.home");
			}

		} catch (IOException e) {
			return null;
		}
		return path;
	}

	public void createConfigFile() {

		prop = new Properties();
		File configFile = new File("./resources/config.properties");
		try (FileWriter writer = new FileWriter(configFile);) {
			prop.setProperty("path", setHomePath());
			prop.store(writer, "RMDocs properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public String setHomePath() {
		return System.getProperty("user.home") + File.separator;
	}


}