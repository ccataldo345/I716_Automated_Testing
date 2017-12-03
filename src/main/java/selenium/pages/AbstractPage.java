package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public abstract class AbstractPage {

    public static final String BASE_URL = "http://enos.itcollege.ee/~mkalmo/selenium";

    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected static WebDriver getDriver() {
        return new HtmlUnitDriver();
    }

    protected WebElement elementById(String id) {
        List<WebElement> elements = driver.findElements(By.id(id));
        return elements.isEmpty() ? null : elements.get(0);
    }

}
