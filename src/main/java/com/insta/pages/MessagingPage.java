package com.insta.pages;

import com.insta.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagingPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href,'/direct/inbox/')]")
    private WebElement messageIcon;

    @FindBy(xpath = "(//div[contains(@class,'x1lliihq')]//a[contains(@href,'/direct/t/')])[1]")
    private WebElement firstChatThread;

    @FindBy(xpath = "//textarea[@placeholder='Message...']")
    private WebElement messageInput;

    @FindBy(xpath = "//div[@role='navigation']//a[contains(@href,'/direct/')]")
    private WebElement messengerNavigationButton;

    @FindBy(xpath = "//button[text()='Not Now']")
    private WebElement notificationPopupNotNow;

    public MessagingPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToMessages() {
        click(messengerNavigationButton);
        logger.info("Clicked on Messages icon");

        // Handle notification popup if it appears
        try {
            if (wait.waitForElementToBeVisible(notificationPopupNotNow) != null) {
                click(notificationPopupNotNow);
                logger.info("Closed notifications popup");
            }
        } catch (Exception e) {
            logger.info("No notification popup appeared");
        }
    }

    public void openFirstChat() {
        wait.waitForElementToBeVisible(firstChatThread);
        click(firstChatThread);
        logger.info("Opened first chat conversation");
    }

    public boolean isMessageThreadOpen() {
        try {
            return wait.waitForElementToBeVisible(messageInput) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateAndOpenFirstChat() {
        navigateToMessages();
        openFirstChat();
    }
}
