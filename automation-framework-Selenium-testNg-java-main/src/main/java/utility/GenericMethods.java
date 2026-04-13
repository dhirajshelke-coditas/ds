package utility;

import DriverRepo.ConfigDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static constants.Constants.configPropPath;

public class GenericMethods {

    public static Properties readPropertiesFile() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configPropPath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
        return properties;
    }

    public static void launchUrl(String url) {
        try {
            ConfigDriver.getDriver().get(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
