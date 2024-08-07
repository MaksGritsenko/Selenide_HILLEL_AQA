package org.example.hillel.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AutorisationPage {

    private static final SelenideElement TITLE = $("[class = 'page-login__title page-access-title']");
    private static final SelenideElement LOGIN_REG_FORM = $("[class = 'page-login ng-star-inserted']");
    private static final SelenideElement REG_BUTTON = $("[class = 'hi-button hi-button--secondary ng-star-inserted']");

    public boolean checkLoginAndRegRogFormIsDisplayed() {
        return LOGIN_REG_FORM.isDisplayed();
    }

    public boolean checkTitleIsCorrect() {
        return TITLE.getText().equals("Login");
    }

    public RegPage clickOnRegButton () {
        REG_BUTTON.shouldBe(visible).click();
        return new RegPage();
    }

}
