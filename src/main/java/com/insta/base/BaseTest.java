package com.insta.base;

import com.insta.utils.DriverManager;
import com.insta.config.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
    protected WebDriver driver;


    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    public WebDriver getDriver() {
        return this.driver;
    }
    @BeforeSuite
    public void beforeSuite() {
        Configuration.loadConfig();
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Configuration.getProperty("baseUrl"));
        logger.info("Started browser and navigated to: " + Configuration.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Closed browser session");
    }
}