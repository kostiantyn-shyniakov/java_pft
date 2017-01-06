package pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
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

    protected void attach(By locator, File file) {
        if (file!=null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }
}
