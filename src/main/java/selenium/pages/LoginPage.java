package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
        if (elementById("login_page") == null) {
            throw new IllegalStateException("not on login page");
        }
    }

    public LoginPage logInExpectingFailure(String user, String pass) {

        // can take samples from SeleniumUsageExample
        // insert user name

        elementById("username_box").sendKeys(user);

        // insert password

        elementById("password_box").sendKeys(pass);

        // click login button

        elementById("log_in_button").click();

        return new LoginPage(driver);
    }

    public MenuPage logIn(String user, String pass) {

        elementById("username_box").sendKeys(user);
        elementById("password_box").sendKeys(pass);
        elementById("log_in_button").click();

        return new MenuPage(driver);
    }

    public String getErrorMessage() {
        WebElement element = elementById("error_message");
        return element == null ? null : element.getText();
    }

    public String getInfoMessage() {
        WebElement element = elementById("info_message");
        return element == null ? null : element.getText();
    }

    public static LoginPage goTo() {
        WebDriver driver = getDriver();
        driver.get(BASE_URL);
        return new LoginPage(driver);
    }

}
