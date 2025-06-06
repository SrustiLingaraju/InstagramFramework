package com.insta.listeners;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.insta.base.BaseTest;
import com.insta.reports.ExtentManager;
import com.insta.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.insta.utils.ScreenshotUtils.logger;


public class TestListener implements ITestListener {
    private ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test Failed: " + result.getName());

        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String testName = result.getMethod().getMethodName();
        String screenshotName = testName + "_" + timestamp + ".png";

        ScreenshotUtils.takeScreenshot(driver, screenshotName);
        logger.info("Screenshot captured: " + screenshotName);
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}