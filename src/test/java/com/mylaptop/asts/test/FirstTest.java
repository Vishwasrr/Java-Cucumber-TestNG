package com.mylaptop.asts.test;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
    }

    @Test(priority = 0)
    public void register() {

    }

    @Test(groups = {"smoke"})  // or dependsOnMethods = "register"
    public void login(){
        String usernameXpath = "//input[@id='user-name']";
        WebElement usernameButton = driver.findElement(By.xpath(usernameXpath));
        usernameButton.click();

        String passwordXpath = "//input[@id='password']";
        WebElement passwordButton = driver.findElement(By.xpath(passwordXpath));
        passwordButton.click();

        String loginXpath = "//input[@id='login-button']";
        WebElement loginButton = driver.findElement(By.xpath(loginXpath));
        loginButton.click();
    }

    @Ignore // or make whole class @Ignore and make this test function (enabled= false)
    public void anotherSample(){

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
