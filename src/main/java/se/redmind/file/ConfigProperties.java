package se.redmind.file;

import se.redmind.util.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigProperties --- Handles the config.properties file that sets initial properties
 *
 * @author Victor Mattsson, Özgur Eken
 */
public class ConfigProperties {

    private String path = "";
    private final String PROPERTY_FILE = "config.properties";
    private Properties properties;

    public ConfigProperties() {
    	this.properties = new Properties();
        initConfig();
    }

    public String getPath() {
        return this.path;
    }

    private final void initConfig() {
        properties.setProperty("path", setHomePath());
        path = System.getProperty("user.dir");
    }


    public void createConfigFile() {

        properties = new Properties();
        File configFile = new File("./resources/config.properties");
        try (FileWriter writer = new FileWriter(configFile)) {
            properties.setProperty("path", setHomePath());
            properties.store(writer, "RMDocs properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String setHomePath() {
        return System.getProperty("user.dir") + File.separator;
    }
}
