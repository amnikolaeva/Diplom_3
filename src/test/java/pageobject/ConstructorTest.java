package pageobject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConstructorTest {

    private WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", "/Users/annanikolaeva/Apps/WebDriver/bin/chromedriver");
//        System.setProperty("webdriver.chrome.driver", "/Users/annanikolaeva/Apps/WebDriver/bin/yandexdriver");
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
    public void checkClickBunChapter() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickSauceChapter();
        objHomePage.clickBunChapter();
        Assert.assertTrue(objHomePage.isSelectedBunChapterDisplayed());
    }

    @Test
    public void checkClickSauceChapter() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickSauceChapter();
        Assert.assertTrue(objHomePage.isSelectedSauceChapterDisplayed());
    }

    @Test
    public void checkClickFillingChapter() {
        HomePageStellarBurger objHomePage = new HomePageStellarBurger(driver);
        objHomePage.clickFillingChapter();
        Assert.assertTrue(objHomePage.isSelectedFillingChapterDisplayed());
    }
}
