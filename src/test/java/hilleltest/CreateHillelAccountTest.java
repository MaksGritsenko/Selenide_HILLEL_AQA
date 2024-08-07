package hilleltest;

import base.BaseTest;
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

    @Test(testName = "Check main hillel page is loaded", priority = 1)
    public void checkMainHillelPageIsOpened() {
        hillelMainPage = open(HOME_HILLEL_URL.getUrl(), HillelMainPage.class);
        webdriver().shouldHave(url(HOME_HILLEL_URL.getUrl()));
        assertTrue(hillelMainPage.checkMainPageIsLoaded(), "Main page is not loaded");
    }

    @Test(testName = "Check autorisation page is opened", priority = 2)
    public void checkAutorisationHillelPageIsOpened() {
        autorisationPage = hillelMainPage.clickOnLogInButton();
        webdriver().shouldHave(url(HILLEL_AUTH_URL.getUrl()));
        assertTrue(autorisationPage.checkTitleIsCorrect(), "Title is not correct");
        assertTrue(autorisationPage.checkLoginAndRegRogFormIsDisplayed(), "Login and registration form is not displayed");
    }

    @Test(testName = "Check reg name and surname page is loaded", priority = 3)
    public void checkRegNameAndSurnamePageIsLoaded() {
        regPage = autorisationPage.clickOnRegButton();
        webdriver().shouldHave(url(HILLEL_REG_URL.getUrl()));
        assertTrue(regPage.checkRegPageIsLoaded(), "Reg page is not loaded");
    }

    @Test(testName = "Check reg phone and email page is loaded", priority = 4)
    public void checkRegPhoneAndEmailPageIsLoaded() {
        regPage.setNameAndSurnameField(REG_USER.getFirstName(), REG_USER.getLastName());
        regPage.clickOnPersonalDataCheckBox();
        regPage.clickOnPublicDataCheckBox();
        regPage.clickOnNextButton();
        assertTrue(regPage.checkRegPageIsLoaded(), "Reg page is not loaded");
    }

    @Test(testName = "Check confirm form page is opened", priority = 5)
    public void checkConfirmFormPageIsOpened() {
        regPage.setPhoneField(REG_USER.getNumber());
        email = EmailGenerator.generateRandomEmail();
        regPage.setEmailField(email);
        regPage.setPasswordField(REG_USER.getPassword());
        regPage.clickOnNextButton();
        assertTrue(regPage.checkConfirmFormIsDisplayed(), "Confirm form is not displayed");
    }

    @Test(testName = "Check mailinator main page is opened", priority = 6)
    public void checkMailinatorMainPageIsOpened() {
        switchTo().newWindow(WindowType.TAB);
        mainMailinatorPage = open("https://www.mailinator.com/", MainMailinatorPage.class);
        webdriver().shouldHave(url(MAILINATOR_MAIN_URL.getUrl()));
        assertTrue(mainMailinatorPage.checkMailinatorIsOpened(), "Mailinator page is not opened");
        mainMailinatorPage.setSearchEmail(email);
        mainMailinatorPage.clickOnGoButton();
        assertTrue(mainMailinatorPage.checkIncomeMessageIsDisplayed(), "Income message is not displayed");
    }

    @Test(testName = "Check confirm email", priority = 7)
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
