package pft.mantis.appmanager;

import org.openqa.selenium.*;

public class BaseHelper {
    protected WebDriver wd;
    protected ApplicationManager app;

    public BaseHelper(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void fillUpField(By locator, String text) {
        WebElement e = wd.findElement(locator);
        if (text!=null) {
            String existingText = e.getAttribute("value");
            if (!text.equals(existingText)) {
                e.clear();
                e.sendKeys(text);
            }
        }
    }
}
