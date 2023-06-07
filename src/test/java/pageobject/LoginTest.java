package pageobject;

import client.UserClient;
import generator.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Browser;
import model.User;
import model.UserCredentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String accessToken;

    static {
        System.setProperty("webdriver.chrome.driver", Browser.getPath(System.getenv("BROWSER_NAME")));
    }

    @Before
    @Description("Открывает страницу приложения, через API создает рандомного пользователя, авторизуется под пользователем, получает токен")
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
        user = UserGenerator.getRandom();
        userClient = new UserClient();
        userClient.create(user);
        accessToken = userClient.login(UserCredentials.from(user))
                .extract().path("accessToken");
    }

    @After
    @Description("Выходит из браузера, через API удаляет созданного пользователя")
    public void teardown() {
        driver.quit();
        userClient.delete(accessToken, UserCredentials.from(user));
    }

    @Test
    @DisplayName("Тест на вход на главной странице")
    @Description("Проверяет вход пользователя по кнопке Вход на главной странице")
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
    @DisplayName("Тест на вход из личного кабинета")
    @Description("Проверяет вход пользователя по кнопке Вход из личного кабинета")
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
    @DisplayName("Тест на вход из формы регистрации")
    @Description("Проверяет вход пользователя по кнопке Вход из формы регистрации")
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
    @DisplayName("Тест на вход из формы восстановления пароля")
    @Description("Проверяет вход пользователя по кнопке Вход из формы восстановления пароля")
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

