package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileWindow extends AbstractPageObject {

    private static final By CONSTRUCTOR = By.xpath(".//svg/p[@class='AppHeader_header__linkText__3q_va ml-2']");

    public ProfileWindow(WebDriver driver) {
        super(driver);
    }
}
