package pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import pft.addressbook.model.GroupData;

import java.util.List;

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
        fillUpField(By.name("address"),contactData.getAddress());
        if (creation) {
            String groupName = contactData.getGroups().iterator().next().getGroupName();
            wd.findElement(By.xpath("//*[@name='new_group']//option[contains(., \""+groupName+"\")]")).click();
        }
        attach(By.name("photo"),contactData.getPhoto());
    }

    public void deleteContact(ContactData deletedContact) {
        wd.findElement(By.cssSelector("input[value='"+deletedContact.getId()+"']")).click();
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editContact(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']",contact.getId())));
        fillContactForm(contact,false);
        click(By.xpath("//*[@id='content']/form[1]/input[1]"));
    }

    public boolean isContactPresent() {
        return isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]/input"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows= wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for(WebElement row : rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']",contact.getId())));
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().
                withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public ContactData detailedInfoForm(ContactData contact) {
        click(By.cssSelector(String.format("a[href='view.php?id=%s']",contact.getId())));
        String detailedInfo = wd.findElement(By.id("content")).getText();
        wd.navigate().back();
        return new ContactData().
                withId(contact.getId()).withDetailedInfo(detailedInfo);
    }

    public void deleteGroupFromContact(ContactData contact, GroupData group) {
        wd.findElement(By.xpath("//*[@name='group']//option[contains(., \""+group.getGroupName()+"\")]")).click();
        wd.findElement(By.cssSelector("input[value='"+contact.getId()+"']")).click();
        click(By.name("remove"));
        click(By.linkText("home"));
        wd.findElement(By.xpath("//*[@name='group']//option[contains(., \"[all]\")]")).click();
    }

    public void addGroupToContact(ContactData contact, GroupData group) {
        wd.findElement(By.cssSelector("input[value='"+contact.getId()+"']")).click();
        wd.findElement(By.xpath("//*[@name='to_group']//option[contains(., \""+group.getGroupName()+"\")]")).click();
        click(By.name("add"));
        click(By.linkText("home"));
    }
}
