package pageobject;

import client.UserClient;
import generator.UserGenerator;
import model.User;
import model.UserCredentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {

    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String accessToken;

    static {
        System.setProperty("webdriver.chrome.driver", "/Users/annanikolaeva/Apps/WebDriver/bin/chromedriver");
//        System.setProperty("webdriver.chrome.driver", "/Users/annanikolaeva/Apps/WebDriver/bin/yandexdriver");
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        user = UserGenerator.getRandom();
        userClient = new UserClient();
        userClient.create(user);
        accessToken = userClient.login(UserCredentials.from(user))
                .extract().path("accessToken");
    }

    @After
    public void teardown() {
        driver.quit();
        userClient.delete(accessToken, UserCredentials.from(user));
    }

    @Test
    public void checkSuccessRegistration() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.clickRegistrationLink();
        RegistrationWindow objRegistration = new RegistrationWindow(driver);
        objRegistration.inputNameField();
        objRegistration.inputEmailField(user.getEmail());
        objRegistration.inputPasswordField(user.getPassword());
        objRegistration.clickRegistrationButton();
        Assert.assertTrue(objLogin.isEntranceButtonDisplayed());
    }

    @Test
    public void checkRegistrationWithIncorrectPassword() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.clickRegistrationLink();
        RegistrationWindow objRegistration = new RegistrationWindow(driver);
        objRegistration.inputNameField();
        objRegistration.inputEmailField(user.getEmail());
        objRegistration.inputIncorrectPasswordField();
        objRegistration.clickRegistrationButton();
        objRegistration.checkEnabledIncorrectPasswordField();
    }
}
