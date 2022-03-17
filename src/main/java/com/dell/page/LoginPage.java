package com.dell.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(xpath = "//*[@id='react-burger-menu-btn']")
    public WebElement logOutBtnDrpDown;

    @FindBy(xpath = "//*[@id='logout_sidebar_link']")
    public WebElement logOutBtn;

    @FindBy(xpath = "//*[@data-test='error']")
    public WebElement errorMsg;

    @FindBy(xpath = "//*[text()='Products']")
    public WebElement title;

    @FindBy(xpath = "//*[@id='user-name']")
    public WebElement username;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement password;

    @FindBy(xpath = "//*[@id='login-button']")
    public WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void loginWithEmptyValues() {
        loginBtn.click();
    }

    public void login(String userName, String password) {
        this.username.sendKeys(userName);
        this.password.sendKeys(password);
        this.loginBtn.click();
    }

    public void loginWithoutUsername(String password) {
        this.password.sendKeys(password);
        this.loginBtn.click();
    }

    public void loginWithoutPassword(String userName) {
        this.username.sendKeys(userName);
        this.loginBtn.click();
    }

    public void logout() {
        this.logOutBtnDrpDown.click();
        this.logOutBtn.click();
    }
}
