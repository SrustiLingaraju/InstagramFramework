package com.insta.tests;

import com.insta.base.BaseTest;
import com.insta.pages.LoginPage;
import com.insta.config.Configuration;
import com.insta.pages.MessagingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                Configuration.getProperty("username"),
                Configuration.getProperty("password")
        );
        // Add assertions here
        // Then navigate to messages and open first chat
      //  Assert.assertTrue(false, "Forcing test to fail for demonstration");



    }
    @Test(priority = 2, description = "Verify message functionality after login",
            dependsOnMethods = "testValidLogin")

    public void testMessageNavigation() {
        MessagingPage messagingPage = new MessagingPage(driver);
        messagingPage.navigateAndOpenFirstChat();

        Assert.assertTrue(messagingPage.isMessageThreadOpen(),
                "Message thread should be open");
    }
    //added srusti-fork
}