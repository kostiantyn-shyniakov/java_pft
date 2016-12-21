package pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void create(ContactData addNewData) {
        click(By.linkText("add new"));
        fillContactForm(addNewData,true);
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    private void fillContactForm(ContactData contactData, boolean creation) {
        fillUpField(By.name("firstname"),contactData.getFirstname());
        fillUpField(By.name("lastname"),contactData.getLastname());
    }

    public void deleteContact(ContactData deletedContact) {

        wd.findElement(By.cssSelector("input[value='"+deletedContact.getId()+"']")).click();
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editContact(ContactData editContactData) {
        click(By.xpath("//input[@value='"+editContactData.getId()+"']/../../td/a/img[@title='Edit']"));
        fillContactForm(editContactData,false);
        click(By.xpath("//*[@id='content']/form[1]/input[1]"));
    }

    public boolean isContactPresent() {
        return isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]/input"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements= wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for(WebElement e : elements){
            String lastname = e.findElement(By.xpath("td[2]")).getText();
            String firstname = e.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
            ContactData group = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
            contacts.add(group);
        }
        return contacts;
    }
}
