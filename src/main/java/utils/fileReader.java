package utils;
//import  src.test.Resources.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constants.reporterConstant.TEST_REPORTER_PROPERTY_FILE_DIRECTORY;


public class fileReader {

    public static String ENV="";
    private static final Logger logger = LogManager.getLogger();

    public static Properties loadProperties() {
        Properties prop = new Properties();
        try {
            InputStream ip = new FileInputStream("src/test/Resources/config.properties");
            prop.load(ip);
            return prop;
        } catch (IOException e) {
           logger.error("Unable to load properties due to:");
           logger.error(e.getMessage());
        }
        return prop;
    }
    private static Properties loadProperties(Path filePath) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Failed to load properties from file: {}", filePath);
            logger.error(e.getMessage());
        }
        return properties;
    }

    public static String getEnvbasedURL(String ENV) throws IOException {
           Properties prop=loadProperties();
           if(prop.getProperty("Environment").equals("QA"))
               ENV= prop.getProperty("QAbaseURL");
           else if(prop.getProperty("Environment").equals("PROD"))
               ENV= prop.getProperty("PRODbaseURL");
           else if(prop.getProperty("Environment").equals("UAT"))
               ENV= prop.getProperty("UATbaseURL");
           return ENV;
    }
    public static String getPropertyFromFile(String propertyName) {
        Properties properties = loadProperties();
        return properties.getProperty(propertyName);
    }
    public static String  readFromFile(String filePath)
    {
        File outputFile = new File(filePath);
        String token="";
        try {
            token= FileUtils.readFileToString(outputFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON response written to output.json");
        return token;
    }
    public static String getTestReporterProperty(String propertyName) {
        return getPropertyFromFile(TEST_REPORTER_PROPERTY_FILE_DIRECTORY, propertyName);
    }
    private static String getPropertyFromFile(Path filePath, String propertyName) {
        Properties properties = loadProperties(filePath);
        return properties.getProperty(propertyName);
    }
    }

