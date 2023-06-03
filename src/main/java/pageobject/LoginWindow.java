package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginWindow extends AbstractPageObject {

    private static final By ENTRANCE_BUTTON = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private static final By RESTORE_PASSWORD = By.xpath(".//div/p[@class='undefined text text_type_main-default text_color_inactive']/a[@class='Auth_link__1fOlj']");
    private static final By REGISTRATION_LINK = By.xpath(".//div/p[@class='undefined text text_type_main-default text_color_inactive mb-4']/a[@class='Auth_link__1fOlj']");

    public LoginWindow(WebDriver driver) {
        super(driver);
    }

    public void clickRegistrationLink() {
        driver.findElement(REGISTRATION_LINK).click();
    }

    public boolean isEntranceButtonDisplayed() {
        checkThatElementIsEnabled(ENTRANCE_BUTTON);
        return driver.findElement(ENTRANCE_BUTTON).isDisplayed();
    }
}
