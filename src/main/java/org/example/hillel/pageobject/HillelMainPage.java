package org.example.hillel.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HillelMainPage {

    private static final SelenideElement TITLE = $("[class='section-content_logo']");
    private static final SelenideElement LOG_IN_BUTTON = $("[class = 'site-nav-btn -lms']");

    @Step("Check main page is loaded")
    public boolean checkMainPageIsLoaded () {
        return TITLE.isDisplayed();
    }

    @Step("Click on login button")
    public AutorisationPage clickOnLogInButton () {
        LOG_IN_BUTTON.shouldBe(visible).click();
        return new AutorisationPage();
    }

}
