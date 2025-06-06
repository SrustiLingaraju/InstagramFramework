package com.insta.pages;

import com.insta.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[text()='Save Info']")
    private WebElement saveInfoButton;

    @FindBy(xpath = "//button[text()='Not Now']")
    private WebElement notNowButton;

    @FindBy(xpath = "//button[text()='Turn On']")
    private WebElement turnOnNotificationsButton;

    @FindBy(xpath = "//p[@role='alert']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        driver.get("https://www.instagram.com/");
        logger.info("Navigated to Instagram login page");
    }

    public void enterUsername(String username) {
        sendKeys(usernameInput, username);
        logger.info("Entered username: " + username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
        logger.info("Entered password");
    }

    public void clickLoginButton() {
        click(loginButton);
        logger.info("Clicked login button");
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        handlePostLoginPopups();
    }

    public void handlePostLoginPopups() {
        try {
            // Handle "Save Info" popup
            if (wait.waitForElementToBeVisible(saveInfoButton) != null) {
                click(notNowButton);
                logger.info("Clicked 'Not Now' on Save Info popup");
            }

            // Handle notifications popup
            if (wait.waitForElementToBeVisible(turnOnNotificationsButton) != null) {
                click(notNowButton);
                logger.info("Clicked 'Not Now' on notifications popup");
            }
        } catch (Exception e) {
            logger.info("No post-login popups appeared or they were already handled");
        }
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.waitForElementToBeInvisible(loginButton);
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isOnLoginPage() {
        try {
            return wait.waitForElementToBeVisible(usernameInput) != null;
        } catch (Exception e) {
            return false;
        }
    }
}