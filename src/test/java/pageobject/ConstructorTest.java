package pageobject;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConstructorTest {

    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    private WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER_PATH"));
    }

    @Before
    @Description("Открывает страницу приложения")
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @After
    @Description("Закрывает браузер")
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Тест на переход к разделу Булки")
    @Description("Проверяет переход по клику на раздел Булки на главной странице")
    public void checkClickBunChapter() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickSauceChapter();
        objHomePage.clickBunChapter();
        Assert.assertTrue(objHomePage.isSelectedBunChapterDisplayed());
    }

    @Test
    @DisplayName("Тест на переход к разделу Соусы")
    @Description("Проверяет переход по клику на раздел Соусы на главной странице")
    public void checkClickSauceChapter() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickSauceChapter();
        Assert.assertTrue(objHomePage.isSelectedSauceChapterDisplayed());
    }

    @Test
    @DisplayName("Тест на переход к разделу Начинки")
    @Description("Проверяет переход по клику на раздел Начинки на главной странице")
    public void checkClickFillingChapter() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickFillingChapter();
        Assert.assertTrue(objHomePage.isSelectedFillingChapterDisplayed());
    }
}
