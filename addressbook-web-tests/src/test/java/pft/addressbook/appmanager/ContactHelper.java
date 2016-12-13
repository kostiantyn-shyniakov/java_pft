package pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void createNewContact(ContactData addNewData) {
        click(By.linkText("add new"));
        fillContactForm(addNewData,true);
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    private void fillContactForm(ContactData contactData, boolean creation) {
        fillUpField(By.name("firstname"),contactData.getFirstname());
        fillUpField(By.name("lastname"),contactData.getLastname());
    }

    public void deleteContact() {

        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]/input"));
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editContact(ContactData editContactData) {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
        fillContactForm(editContactData,false);
        click(By.xpath("//*[@id='content']/form[1]/input[1]"));
    }

    public boolean isContactPresent() {
        return isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]/input"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> groups = new ArrayList<ContactData>();
        List<WebElement> elements= wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for(WebElement e : elements){
            String lastname = e.findElement(By.xpath("td[2]")).getText();
            String firstname = e.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
            ContactData group = new ContactData(id, firstname, lastname);
            groups.add(group);
        }
        return groups;
    }
}
