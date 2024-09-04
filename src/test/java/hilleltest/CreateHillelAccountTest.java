package hilleltest;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.example.hillel.pageobject.AutorisationPage;
import org.example.hillel.pageobject.HillelMainPage;
import org.example.hillel.pageobject.RegPage;
import org.example.hillel.testdata.EmailGenerator;
import org.example.mailinator.pageobject.MainMailinatorPage;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.example.hillel.testdata.Url.*;
import static org.example.hillel.testdata.User.REG_USER;
import static org.testng.Assert.assertTrue;

public class CreateHillelAccountTest extends BaseTest {

    private RegPage regPage;
    private HillelMainPage hillelMainPage;
    private AutorisationPage autorisationPage;
    private MainMailinatorPage mainMailinatorPage;
    private String email;

    @Test(priority = 1 , testName = "Check main hillel page is loaded")
    @Owner(value = "Hrytsenko")
    @Description("Check main hillel page is loaded")
    public void checkMainHillelPageIsOpened() {
        hillelMainPage = open(HOME_HILLEL_URL.getUrl(), HillelMainPage.class);
        webdriver().shouldHave(url(HOME_HILLEL_URL.getUrl()));
        assertTrue(hillelMainPage.checkMainPageIsLoaded(), "Main page is not loaded");
    }

    @Test(priority = 2, testName = "Check autorisation page is opened")
    @Owner(value = "Hrytsenko")
    @Description("Check autorisation page is opened")
    public void checkAutorisationHillelPageIsOpened() {
        autorisationPage = hillelMainPage.clickOnLogInButton();
        webdriver().shouldHave(url(HILLEL_AUTH_URL.getUrl()));
        assertTrue(autorisationPage.checkTitleIsCorrect(), "Title is not correct");
        assertTrue(autorisationPage.checkLoginAndRegRogFormIsDisplayed(), "Login and registration form is not displayed");
    }

    @Test(priority = 3, testName = "Check reg name and surname page is loaded")
    @Owner(value = "Hrytsenko")
    @Description("Check reg name and surname page is loaded")
    public void checkRegNameAndSurnamePageIsLoaded() {
        regPage = autorisationPage.clickOnRegButton();
        webdriver().shouldHave(url(HILLEL_REG_URL.getUrl()));
        assertTrue(regPage.checkRegPageIsLoaded(), "Reg page is not loaded");
    }

    @Test(priority = 4, testName = "Check reg phone and email page is loaded")
    @Owner(value = "Hrytsenko")
    @Description("Check reg phone and email page is loaded")
    public void checkRegPhoneAndEmailPageIsLoaded() {
        regPage.setNameAndSurnameField(REG_USER.getFirstName(), REG_USER.getLastName());
        regPage.clickOnPersonalDataCheckBox();
        regPage.clickOnPublicDataCheckBox();
        regPage.clickOnNextButton();
        assertTrue(regPage.checkRegPageIsLoaded(), "Reg page is not loaded");
    }

    @Test(priority = 5, testName = "Check confirm form page is opened")
    @Owner(value = "Hrytsenko")
    @Description("Check confirm form page is opened")
    public void checkConfirmFormPageIsOpened() {
        regPage.setPhoneField(REG_USER.getNumber());
        email = EmailGenerator.generateRandomEmail();
        regPage.setEmailField(email);
        regPage.setPasswordField(REG_USER.getPassword());
        regPage.clickOnNextButton();
        assertTrue(regPage.checkConfirmFormIsDisplayed(), "Confirm form is not displayed");
    }

    @Test(priority = 6, testName = "Check mailinator main page is opened")
    @Owner(value = "Hrytsenko")
    @Description("Check mailinator main page is opened")
    public void checkMailinatorMainPageIsOpened() {
        switchTo().newWindow(WindowType.TAB);
        mainMailinatorPage = open("https://www.mailinator.com/", MainMailinatorPage.class);
        webdriver().shouldHave(url(MAILINATOR_MAIN_URL.getUrl()));
        assertTrue(mainMailinatorPage.checkMailinatorIsOpened(), "Mailinator page is not opened");
        mainMailinatorPage.setSearchEmail(email);
        mainMailinatorPage.clickOnGoButton();
        assertTrue(mainMailinatorPage.checkIncomeMessageIsDisplayed(), "Income message is not displayed");
    }

    @Test(priority = 7, testName = "Check confirm email")
    @Owner(value = "Hrytsenko")
    @Description("Check confirm email")
    public void checkEmailIsOpened() {
        mainMailinatorPage.clickOnIncomeMessage();
        assertTrue(mainMailinatorPage.checkEmailContainerIsDisplayed(), "Email container is not displayed");
        mainMailinatorPage.switchToFrame();
        String confirmCode = mainMailinatorPage.getConfirmCode();
        switchTo().window(0);
        regPage.setConfirmCodeField(confirmCode);
        regPage.clickOnSubmitButton();
        webdriver().shouldHave(url(HILLEL_STORE.getUrl()));
    }
}
