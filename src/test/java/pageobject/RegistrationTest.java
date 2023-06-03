package pageobject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {

    private WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", "/Users/annanikolaeva/Apps/WebDriver/bin/chromedriver");
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkSuccessRegistration() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.clickRegistrationLink();
        RegistrationWindow objRegistration = new RegistrationWindow(driver);
        objRegistration.inputNameField();
        objRegistration.inputEmailField();
        objRegistration.inputPasswordField();
        objRegistration.clickRegistrationButton();
        Assert.assertTrue(objLogin.isEntranceButtonDisplayed());
    }
}
