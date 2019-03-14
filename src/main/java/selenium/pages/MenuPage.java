package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage extends AbstractPage {

    public MenuPage(WebDriver driver) {
        super(driver);
        // check that we are on the correct page
        // sample from LoginPage
        if (elementById("menu_page") == null) {
            throw new IllegalStateException("not on menu page");
        }
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

    public ListPage goToUserListPage() {
        elementById("show_users_link").click();
        return new ListPage(driver);
    }

    public FormPage goToAddUserPage() {
        elementById("add_user_link").click();
        return new FormPage(driver);
    }

}
