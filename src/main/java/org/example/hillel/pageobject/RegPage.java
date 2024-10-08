package org.example.hillel.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegPage {
    private static final SelenideElement PASSWORD_FIELD = $(By.id("password-confirm-new"));
    private static final SelenideElement SUBMIT_BUTTON = $("[type = 'submit']");
    private static final SelenideElement PUBLIC_DATA = $("[formcontrolname='terms']");
    private static final SelenideElement EMAIL_FIELD = $("[formcontrolname='email']");
    private static final SelenideElement CONFIRM_PASSWORD_FIELD = $(By.id("password-confirm-confirm"));
    private static final SelenideElement PHONE_FIELD = $("[placeholder = '39 123 4567']");
    private static final SelenideElement NAME_FIELD = $("[formcontrolname='first_name']");
    private static final SelenideElement CONFIRM_CODE_FIELD = $("[formcontrolname='code']");
    private static final SelenideElement SURNAME_FIELD = $("[formcontrolname='last_name']");
    private static final SelenideElement PERSONAL_DATA = $("[formcontrolname='data_processing']");
    private static final SelenideElement NEXT_BUTTON = $("[class = 'hi-button ng-star-inserted']");
    private static final SelenideElement TITLE = $("[class = 'registration__head page-access-title']");
    private static final SelenideElement CONFIRM_FORM = $("[class = 'registration-confirm-email__description page-access-description']");

    @Step("Check reg page is loaded")
    public boolean checkRegPageIsLoaded() {
        return TITLE.getText().equals("Registration");
    }

    @Step("Click on personal data")
    public void clickOnPersonalDataCheckBox() {
        PERSONAL_DATA.click();
    }

    @Step("Click on public data")
    public void clickOnPublicDataCheckBox() {
        PUBLIC_DATA.click();
    }

    @Step("Set name and surname field")
    public void setNameAndSurnameField(String name, String surname) {
        NAME_FIELD.setValue(name);
        SURNAME_FIELD.setValue(surname);
    }

    @Step("Click on next button")
    public void clickOnNextButton() {
        NEXT_BUTTON.click();
    }

    @Step("Set phone field")
    public void setPhoneField(String phoneNumber) {
        PHONE_FIELD.setValue(phoneNumber);
    }

    @Step("Set password field")
    public void setPasswordField(String password) {
        PASSWORD_FIELD.setValue(password);
        CONFIRM_PASSWORD_FIELD.setValue(password);
    }

    @Step("Set email field")
    public void setEmailField(String email) {
        EMAIL_FIELD.setValue(email);
    }

    @Step("Check confirm form is displayed")
    public boolean checkConfirmFormIsDisplayed() {
        return CONFIRM_FORM.shouldBe(visible).isDisplayed();
    }

    @Step("Set confirm code")
    public void setConfirmCodeField(String code) {
        CONFIRM_CODE_FIELD.setValue(code);
    }

    @Step("Click on submit button")
    public void clickOnSubmitButton() {
        SUBMIT_BUTTON.click();
    }
}
