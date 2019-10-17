package com.cptest.qa.base;

import com.cptest.qa.util.FileUtil;
import com.cptest.qa.util.TestUtil;
import com.cptest.qa.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;

    public TestBase() {
        try {
            prop = new Properties();
            String envConfigFile ;

            String environment = System.getProperty("env");
            //System.out.println("System environment : a" + environment+"a");

            if (environment==null||environment.length() == 0){
               // System.out.println("Environment is empty");
                envConfigFile = FileUtil.getPropertyFromFile("config/common.properties","default_config");
            }
            else{
                envConfigFile = FileUtil.getPropertyFromFile("config/common.properties", environment);
            }

            System.out.println("Environment config property : " + envConfigFile);

            File file = new FileUtil().getFileFromResources(envConfigFile);
            //System.out.println(file.getAbsoluteFile());

            FileInputStream ip = new FileInputStream(file.getAbsoluteFile());
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void initialization() {
        String browserName = FileUtil.getPropertyFromFile("config/common.properties","browser");

        if (browserName.equals("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "/Users/bikram.vikash/Downloads/chromedriver");
            System.setProperty("webdriver.chrome.driver", FileUtil.getPropertyFromFile("config/common.properties","chrome_driver"));

            driver = new ChromeDriver();
        } else if (browserName.equals("FF")) {
            System.setProperty("webdriver.gecko.driver", FileUtil.getPropertyFromFile("config/common.properties","gecko_driver"));
            driver = new FirefoxDriver();
        }


        e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));

    }



}
