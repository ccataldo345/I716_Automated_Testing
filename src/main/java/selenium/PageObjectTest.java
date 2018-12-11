package selenium;

import org.junit.jupiter.api.Test;
import selenium.pages.FormPage;
import selenium.pages.LoginPage;
import selenium.pages.MenuPage;
import selenium.pages.ListPage;

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
        System.out.println(loginPage.getErrorMessage());

        assertThat(loginPage.getErrorMessage(), is("Login failed!"));
    }

    @Test
    public void loginSucceedsWithCorrectCredentials() {
        LoginPage loginPage = LoginPage.goTo();
        // System.out.println(loginPage.getInfoMessage());

        MenuPage menuPage = loginPage.logIn(USERNAME, CORRECT_PASSWORD);
        System.out.println(menuPage.getInfoMessage());
        System.out.println(menuPage.getLogOutLink());

        // assertThat(... contains info message;
        assertThat(menuPage.getInfoMessage(), is("Logged in!"));

        // assertThat(... contains log out link;
        assertThat(menuPage.getLogOutLink(), is("Log out"));
    }

    @Test
    public void canLogOut() {
        // MenuPage menuPage = ... log in ...;
        LoginPage loginPage = LoginPage.goTo();
        MenuPage menuPage = loginPage.logIn(USERNAME, CORRECT_PASSWORD);

        // LoginPage loginPage = menuPage.logOut();
        loginPage = menuPage.logOut();
        System.out.println(loginPage.getInfoMessage());

        // assertThat(... contains info message;
        assertThat(loginPage.getInfoMessage(), is("Logged out!"));
    }

    @Test
    public void userListContainsCorrectUsernameAndPassword() {
        LoginPage loginPage = LoginPage.goTo();
        MenuPage menuPage = loginPage.logIn(USERNAME, CORRECT_PASSWORD);

        ListPage listPage = menuPage.goToUserListPage();
        System.out.println(listPage.showAllUsers());

        assertThat(listPage.showAllUsers().contains("<div username=\"user\" password=\"1\">"), is(true));
    }

    @Test
    public void addNewUser_verifyIsAddedToUserList() {
        LoginPage loginPage = LoginPage.goTo();
        MenuPage menuPage = loginPage.logIn(USERNAME, CORRECT_PASSWORD);

        FormPage formPage = menuPage.goToAddUserPage();
        formPage.addNewUser("user2", "2");
        ListPage listPage = menuPage.goToUserListPage();
        System.out.println(listPage.showAllUsers());

        assertThat(listPage.showAllUsers().contains("user2"), is(true));
    }

}
