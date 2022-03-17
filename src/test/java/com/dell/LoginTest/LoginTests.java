package com.dell.LoginTest;

import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dell.page.LoginPage;
import com.dell.util.Util;

import java.io.FileNotFoundException;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private JsonObject userInfo;

    @BeforeClass
    public void init() throws FileNotFoundException {
        loginPage = new LoginPage(driver);
        userInfo = Util.getJsonObject("src/main/resources/UserInfo.json");
    }

    @BeforeMethod
    public void navigateToSauceDemo() {
        driver.get(userInfo.get("url").getAsString());
    }

    /**
     * A test case to test a valid login
     */
    @Test
    public void validLogin() {
        loginPage.login(userInfo.get("userName1").getAsString(), userInfo.get("password").getAsString());

        String expectedValue = "PRODUCTS";
        String actualValue = loginPage.title.getText();
        Assert.assertEquals(actualValue, expectedValue);
        loginPage.logout();
    }

    /**
     * A test case to test the login with empty values
     */
    @Test
    public void loginWithoutCredentials() {
        loginPage.loginWithEmptyValues();

        String expectedErrorMsg = "Epic sadface: Username is required";
        String actualErrorMsg = loginPage.errorMsg.getText();

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    /**
     * This test is testing the login with an invalid username and a valid password
     */
    @Test
    public void loginWithInvalidUsernameAndCorrectPassword() {
        loginPage.login(userInfo.get("invalidUserName").getAsString(), userInfo.get("password").getAsString());
        String expectedErrorMsg = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorMsg = loginPage.errorMsg.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    /**
     * A test case to test the login with a valid username and an invalid password
     */
    @Test
    public void loginWithInvalidPasswordAndCorrectUsername() {
        loginPage.login(userInfo.get("userName1").getAsString(), userInfo.get("invalidPassword").getAsString());
        String expectedErrorMsg = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorMsg = loginPage.errorMsg.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    /**
     * A test case to test the login with invalid username and invalid password
     */
    @Test
    public void invalidUsernameAndPassword() {
        loginPage.login(userInfo.get("invalidUserName").getAsString(), userInfo.get("invalidPassword").getAsString());
        String expectedErrorMsg = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorMsg = loginPage.errorMsg.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    /**
     * A test case to test the login without adding the username
     */
    @Test
    public void noUsernameLogin() {
        loginPage.loginWithoutUsername(userInfo.get("password").getAsString());
        String expectedErrorMsg = "Epic sadface: Username is required";
        String actualErrorMsg = loginPage.errorMsg.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    /**
     * A test case to test the login without adding the password
     */
    @Test
    public void loginWithoutPassword() {
        loginPage.loginWithoutPassword(userInfo.get("password").getAsString());
        String expectedErrorMsg = "Epic sadface: Password is required";
        String actualErrorMsg = loginPage.errorMsg.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
}
