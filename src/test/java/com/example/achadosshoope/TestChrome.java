package com.example.achadosshoope;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestChrome {

        public static void main(String[] args) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\thoma\\Desktop\\webdrivers\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.google.com");
        }
    }

