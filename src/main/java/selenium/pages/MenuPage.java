package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage extends AbstractPage {

    public MenuPage(WebDriver driver) {
        super(driver);
        // check that we are on the correct page
        // sample from LoginPage
        /*if (elementById("login_page") == null) {
            throw new IllegalStateException("not on login page");
        }*/
    }

    public Object getInfoMessage() {
        WebElement element = elementById("info_message");
        return element == null ? null : element.getText();
    }

    public Object getLogOutLink() {
        WebElement element = elementById("log_out_link");
        return element == null ? null : element.getText();
    }

    public LoginPage logOut() {
        elementById("log_out_link").click();
        return new LoginPage(driver);

    }
}
