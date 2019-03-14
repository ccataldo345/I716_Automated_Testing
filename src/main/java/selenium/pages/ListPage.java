package selenium.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.rmi.server.UnicastServerRef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListPage extends AbstractPage {

    public ListPage(WebDriver driver) {
        super(driver);
        if (elementById("list_page") == null) {
            throw new IllegalStateException("not on user list page");
        }
    }

    public String showAllUsers() {

        List<WebElement> list = elementById("user_list")
                .findElements(By.tagName("div"));

        //System.out.println(Arrays.toString(list.toArray()));
        return Arrays.toString(list.toArray());
    }

}
