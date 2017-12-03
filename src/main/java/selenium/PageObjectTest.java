package selenium;

import org.junit.jupiter.api.Test;
import selenium.pages.LoginPage;
import selenium.pages.MenuPage;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PageObjectTest {

    private static final String USERNAME = "user";
    private static final String CORRECT_PASSWORD = "1";
    private static final String WRONG_PASSWORD = "2";

    @Test
    public void loginFailsWithFalseCredentials() {
        LoginPage loginPage = LoginPage.goTo();

        loginPage = loginPage.logInExpectingFailure(USERNAME, WRONG_PASSWORD);

        assertThat(loginPage.getErrorMessage(), is(notNullValue()));
    }

    @Test
    public void loginSucceedsWithCorrectCredentials() {
        LoginPage loginPage = LoginPage.goTo();

        MenuPage menuPage = loginPage.logIn(USERNAME, CORRECT_PASSWORD);

        // assertThat(... contains info message;

        // assertThat(... contains log out link;
    }

    @Test
    public void canLogOut() {
        // MenuPage menuPage = ... log in ...;

        // LoginPage loginPage = menuPage.logOut();

        // assertThat(... contains info message;
    }


}
