package com.cptest.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class FileUtil {

    // get file from classpath, resources folder
    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    public static String getPropertyFromFile(String fileName, String propertyName) {

            File file = new FileUtil().getFileFromResources(fileName);
            System.out.println(file.getAbsoluteFile());
            Properties properties = new Properties();

            try {
                properties.load(new FileInputStream(file.getAbsoluteFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties.getProperty(propertyName);
        }

}
