package pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by kshyniakov on 19.01.2017.
 */
public class RegistrationHelper extends BaseHelper{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String emailOrPassword, String link) {
        wd.get(app.getProperty("web.baseUrl")+ link);
        fillUpField(By.name("username"), username);
        if (link.equals("/signup_page.php")) fillUpField(By.name("email"), emailOrPassword);
        else fillUpField(By.name("password"), emailOrPassword);
        click(By.cssSelector("input.button"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        fillUpField(By.name("password"), password);
        fillUpField(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

    public String resetUserPassword() {
        click(By.xpath("//a[.=\"Manage Users\"]"));
        List<WebElement> rows= wd.findElements(By.xpath("//tr[@class[contains(., \"row\")]]"));
        rows.remove(0);
        rows.remove(0);
        WebElement e = rows.iterator().next();
        List<WebElement> cells = e.findElements(By.tagName("td"));
        String userEmail = cells.get(2).getText();
        String username = cells.get(0).getText();
        cells.get(0).findElement(By.xpath("//a[.=\""+username+"\"]")).click();
        click(By.cssSelector("input[value='Reset Password']"));
        return userEmail;
    }
}
