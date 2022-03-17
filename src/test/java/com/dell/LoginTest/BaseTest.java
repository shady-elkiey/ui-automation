package com.dell.LoginTest;

import org.openqa.selenium.Dimension;
import com.dell.util.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void launchApplication() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
    }

    @AfterMethod
    public void takeScreenShotForFailingTests(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            Util.takeScreenShot(driver, result.getName());
        }
    }

    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}
