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
 */
public class ConfigProperties {

    private String path = "";
    private String propertyFileName;
    private Properties properties;

    public ConfigProperties() {
        propertyFileName = "config.properties";
        initConfig();
    }

    public String getPath() {
        return this.path;
    }

    private void initConfig() {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);) {
            properties = new Properties();

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                createConfigFile();
            }

            setPathFromProperty();

            if (path.equals("")) {
                properties.setProperty("path", setHomePath());
                path = System.getProperty("user.dir");
            }

        } catch (IOException e) {
            createConfigFile();
        }
    }

    public void setPathFromProperty() {
        path = properties.getProperty("path");
    }

    public void createConfigFile() {

        properties = new Properties();
        File configFile = new File("./resources/config.properties");
        try (FileWriter writer = new FileWriter(configFile);) {
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