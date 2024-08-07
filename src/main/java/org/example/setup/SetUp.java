package org.example.setup;

import com.codeborne.selenide.Configuration;
import org.example.hillel.custom.ChromeWebDriverProvider;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class SetUp {

    public void setUp () {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 10000;
        Configuration.browser = ChromeWebDriverProvider.class.getName();
    }

    public void quitDriver () {
        closeWebDriver();
    }



}
