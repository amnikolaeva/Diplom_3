package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationWindow extends AbstractPageObject {

    private static final By NAME_FIELD = By.xpath("//fieldset[@class='Auth_fieldset__1QzWN mb-6'][1]/div[@class='input__container']/div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@class='text input__textfield text_type_main-default']");
    private static final By SELECTED_NAME_FIELD = By.xpath("//div[@class='input pr-6 pl-6 input_type_text input_size_default input_status_active']/input[@class='text input__textfield text_type_main-default']");
    private static final By EMAIL_FIELD = By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][2]/div[@class='input__container']/div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@class='text input__textfield text_type_main-default']");
    private static final By SELECTED_EMAIL_FIELD = By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][2]/div[@class='input__container']/div[@class='input pr-6 pl-6 input_type_text input_size_default input_status_active']/input[@class='text input__textfield text_type_main-default']");
    private static final By PASSWORD_FIELD = By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][3]/div[@class='input__container']/div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@class='text input__textfield text_type_main-default']");
    private static final By SELECTED_PASSWORD_FIELD = By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][3]/div[@class='input__container']/div[@class='input pr-6 pl-6 input_type_password input_size_default input_status_active']/input[@class='text input__textfield text_type_main-default']");
    private static final By REGISTRATION_BUTTON = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private static final By INCORRECT_PASSWORD = By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][3]/div[@class='input__container']/p[@class='input__error text_type_main-default']");
    private static final By ENTRANCE_BUTTON = By.xpath(".//div/p[@class='undefined text text_type_main-default text_color_inactive mb-4']/a[@class='Auth_link__1fOlj']");

    public RegistrationWindow(WebDriver driver) {
        super(driver);
    }

    public void inputNameField() {
        driver.findElement(NAME_FIELD).click();
        driver.findElement(SELECTED_NAME_FIELD).sendKeys("kotevidze");
    }

    public void inputEmailField() {
        driver.findElement(EMAIL_FIELD).click();
        driver.findElement(SELECTED_EMAIL_FIELD).sendKeys("amnik21@yandex.ru");
    }

    public void inputPasswordField() {
        driver.findElement(PASSWORD_FIELD).click();
        driver.findElement(SELECTED_PASSWORD_FIELD).sendKeys("123456");
    }

    public void clickRegistrationButton() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }
}
