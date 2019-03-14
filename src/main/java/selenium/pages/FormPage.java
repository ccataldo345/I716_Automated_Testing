package selenium.pages;

import org.openqa.selenium.WebDriver;

public class FormPage extends AbstractPage {

    public FormPage(WebDriver driver) {
        super(driver);
        if (elementById("form_page") == null) {
            throw new IllegalStateException("not on add user page");
        }
    }

    public MenuPage addNewUser(String user, String pass) {

        elementById("username_box").sendKeys(user);
        elementById("password_box").sendKeys(pass);
        elementById("add_button").click();

        return new MenuPage(driver);
    }

}
