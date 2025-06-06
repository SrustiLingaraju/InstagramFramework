package com.insta.utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    public static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    public static String takeScreenshot(WebDriver driver, String fileName) {
        try {
            // Create screenshots directory if it doesn't exist
            new File(SCREENSHOT_DIR).mkdirs();

            // Take screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destinationPath = SCREENSHOT_DIR + fileName;
            File destination = new File(destinationPath);

            FileUtils.copyFile(screenshotFile, destination);
            logger.info("Screenshot saved: " + destinationPath);
            return destinationPath;

        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
