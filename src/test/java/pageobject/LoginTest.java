package pageobject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", "/Users/annanikolaeva/Apps/WebDriver/bin/chromedriver");
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    public void checkLoginAtHomePage() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.inputEmailField();
        objLogin.inputPasswordField();
        objLogin.clickLoginButton();
        Assert.assertTrue(objHomePage.isOrderButtonDisplayed());
    }
}

