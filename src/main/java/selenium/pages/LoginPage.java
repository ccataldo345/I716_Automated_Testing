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

        // insert user name
        // insert password
        // click login button

        // sample from SeleniumUsageExample

        return new LoginPage(driver);
    }

    public MenuPage logIn(String user, String pass) {
        return null;
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
