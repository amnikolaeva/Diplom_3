package pageobject;

import client.UserClient;
import generator.UserGenerator;
import model.User;
import model.UserCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProfileTest {

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
    public void checkClickAtProfile() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickProfileButton();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.isEntranceButtonDisplayed();
    }

    @Test
    public void checkClickAtConstructor() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.inputEmailField(user.getEmail());
        objLogin.inputPasswordField(user.getPassword());
        objLogin.clickLoginButton();
        objHomePage.clickProfileButton();
        ProfileWindow objProfile = new ProfileWindow(driver);
        objProfile.clickConstructorButton();
        objHomePage.isAssembleBurgerChapterDisplayed();
    }

    @Test
    public void checkClickAtLogo() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.inputEmailField(user.getEmail());
        objLogin.inputPasswordField(user.getPassword());
        objLogin.clickLoginButton();
        objHomePage.clickProfileButton();
        ProfileWindow objProfile = new ProfileWindow(driver);
        objProfile.clickLogoButton();
        objHomePage.isAssembleBurgerChapterDisplayed();
    }

    @Test
    public void checkLogout() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickAccountLogin();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.inputEmailField(user.getEmail());
        objLogin.inputPasswordField(user.getPassword());
        objLogin.clickLoginButton();
        objHomePage.clickProfileButton();
        ProfileWindow objProfile = new ProfileWindow(driver);
        objProfile.clickLogoutButton();
        objLogin.isEntranceButtonDisplayed();
    }
}
