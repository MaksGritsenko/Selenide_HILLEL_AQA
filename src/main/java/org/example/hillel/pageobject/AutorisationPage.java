package org.example.hillel.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AutorisationPage {

    private static final SelenideElement TITLE = $("[class = 'page-login__title page-access-title']");
    private static final SelenideElement LOGIN_REG_FORM = $("[class = 'page-login ng-star-inserted']");
    private static final SelenideElement REG_BUTTON = $("[class = 'hi-button hi-button--secondary ng-star-inserted']");

    @Step("Check login and reg form is displayed")
    public boolean checkLoginAndRegRogFormIsDisplayed() {
        return LOGIN_REG_FORM.isDisplayed();
    }

    @Step("Check is title correct")
    public boolean checkTitleIsCorrect() {
        return TITLE.getText().equals("Login");
    }

    @Step("Click on reg button")
    public RegPage clickOnRegButton () {
        REG_BUTTON.shouldBe(visible).click();
        return new RegPage();
    }

}
