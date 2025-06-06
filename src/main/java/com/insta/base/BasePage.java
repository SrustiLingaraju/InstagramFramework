
package com.insta.base;

import com.insta.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils wait;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    protected void sendKeys(WebElement element, String text) {
        wait.waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
        logger.info("Typed text: " + text);
    }

    protected void click(WebElement element) {
        wait.waitForElementToBeClickable(element);
        element.click();
        logger.info("Clicked element: " + element);
    }

    protected String getText(WebElement element) {
        wait.waitForElementToBeVisible(element);
        return element.getText();
    }
}