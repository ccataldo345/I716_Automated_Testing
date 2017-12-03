package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class SeleniumUsageExample {

    private WebDriver driver = new HtmlUnitDriver();

    @Test
    public void showsHowToUseSelenium() {
        driver.get("http://enos.itcollege.ee/~mkalmo/selenium");

        elementById("username_box").sendKeys("user");
        elementById("password_box").sendKeys("1");

        elementById("log_in_button").click();

        elementById("show_users_link").click();

        List<WebElement> rows = elementById("user_list")
                .findElements(By.tagName("div"));

        for (WebElement row : rows) {
            System.out.println(row.getAttribute("username"));
            System.out.println(row.getAttribute("password"));
        }

        System.out.println(driver.getPageSource());
    }

    public WebElement elementById(String id) {
        List<WebElement> elements = driver.findElements(By.id(id));
        return elements.isEmpty() ? null : elements.get(0);
    }

    @AfterEach
    public void closeDriver() {
        driver.close();
    }
}
