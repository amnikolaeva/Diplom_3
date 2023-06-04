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

public class LoginTest {

    private WebDriver driver;
    private UserClient userClient;
    private User user;
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
    public void checkLoginAtHomePage() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.inputEmailField(user.getEmail());
        objLogin.inputPasswordField(user.getPassword());
        objLogin.clickLoginButton();
        Assert.assertTrue(objHomePage.isOrderButtonDisplayed());
    }

    @Test
    public void checkLoginFromProfile() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickProfileButton();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.inputEmailField(user.getEmail());
        objLogin.inputPasswordField(user.getPassword());
        objLogin.clickLoginButton();
        Assert.assertTrue(objHomePage.isOrderButtonDisplayed());
    }

    @Test
    public void checkLoginFromRegistrationForm() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.clickRegistrationLink();
        RegistrationWindow objRegistration = new RegistrationWindow(driver);
        objRegistration.clickEntranceButton();
        objLogin.inputEmailField(user.getEmail());
        objLogin.inputPasswordField(user.getPassword());
        objLogin.clickLoginButton();
        Assert.assertTrue(objHomePage.isOrderButtonDisplayed());
    }

    @Test
    public void checkLoginFromRestorePasswordLink() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.clickRestorePasswordLink();
        RestorePasswordWindow objRestorePassword = new RestorePasswordWindow(driver);
        objRestorePassword.clickEntranceLink();
        objLogin.inputEmailField(user.getEmail());
        objLogin.inputPasswordField(user.getPassword());
        objLogin.clickLoginButton();
        Assert.assertTrue(objHomePage.isOrderButtonDisplayed());
    }
}

