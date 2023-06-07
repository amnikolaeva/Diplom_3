package pageobject;

import client.UserClient;
import generator.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Browser;
import model.User;
import model.UserCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProfileTest {

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
    @DisplayName("Тест на переход по клику на Личный кабинет")
    @Description("Проверяет переход по клику на Личный кабинет на главной странице")
    public void checkClickAtProfile() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickProfileButton();
        LoginWindow objLogin = new LoginWindow(driver);
        objLogin.isEntranceButtonDisplayed();
    }

    @Test
    @DisplayName("Тест на переход по клику Конструктор из Личного кабинета")
    @Description("Проверяет переход из Личного кабинета на главную страницу по кнопке Конструктор")
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
    @DisplayName("Тест на переход к логотипу из Личного кабинета")
    @Description("Проверяет переход из Личного кабинета на главную страницу по клику на Логотип")
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
    @DisplayName("Тест на разлогинирование")
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
