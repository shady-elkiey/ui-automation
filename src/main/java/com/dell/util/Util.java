package com.dell.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Util {

    private Util() {

    }

    public static void takeScreenShot(WebDriver driver, String screenshotName) throws IOException {
        File source = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File("src/test/resources/" + screenshotName + ".png"));
    }

    public static JsonObject getJsonObject(String path) throws FileNotFoundException {
        return JsonParser.parseReader(new FileReader(path)).getAsJsonObject();
    }
}
