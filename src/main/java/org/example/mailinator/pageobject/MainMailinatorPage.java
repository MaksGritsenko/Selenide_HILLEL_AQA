package org.example.mailinator.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.hillel.pageobject.RegPage;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class MainMailinatorPage {

    private static final SelenideElement SEARCH_FIELD = $(By.id("search"));
    private static final SelenideElement IFRAME = $("[id='html_msg_body']");
    private static final SelenideElement CONFIRM_CODE = $x("(//td//h1)[2]");
    private static final SelenideElement INCOME_MESSAGE = $("[ng-repeat = 'email in emails']");
    private static final SelenideElement GO_BUTTON = $("[value = 'Search for public inbox for free']");
    private static final SelenideElement EMAIL_CONTAINER = $("[class = 'wrapper-message-tabs m-x-32']");
    private static final SelenideElement TITLE = $("[title = 'Mailinator-Millions of Inboxes. All yours.']");

    public boolean checkMailinatorIsOpened() {
        return TITLE.isDisplayed();
    }

    public void setSearchEmail(String email) {
        SEARCH_FIELD.setValue(email);
    }

    public void clickOnGoButton() {
        GO_BUTTON.click();
    }

    public boolean checkIncomeMessageIsDisplayed() {
        return INCOME_MESSAGE.shouldBe(visible).isDisplayed();
    }

    public MainMailinatorPage clickOnIncomeMessage() {
          INCOME_MESSAGE.click();
        return this;
    }

    public boolean checkEmailContainerIsDisplayed() {
        return EMAIL_CONTAINER.is(visible, ofSeconds(10));
    }

    public String getConfirmCode() {
        return CONFIRM_CODE.shouldBe(visible).getText();
    }

    public void switchToFrame () {
        Selenide.switchTo().frame(IFRAME);
    }
}
