package pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import pft.addressbook.model.ContactData;

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
        fillUpField(By.name("middlename"),contactData.getMiddlename());
        fillUpField(By.name("lastname"),contactData.getLastname());
        fillUpField(By.name("nickname"),contactData.getNickname());
        fillUpField(By.name("title"),contactData.getTitle());
        fillUpField(By.name("company"),contactData.getCompany());
        fillUpField(By.name("address"),contactData.getAddress());
        fillUpField(By.name("home"),contactData.getHomephone());
        fillUpField(By.name("email"),contactData.getEmail());
        fillUpField(By.name("homepage"),contactData.getHomepage());
        click(By.xpath("//div[@id='content']/form/select[1]//option[18]"));
        click(By.xpath("//div[@id='content']/form/select[2]//option[10]"));
        fillUpField(By.name("byear"),contactData.getBirthyear());
        click(By.xpath("//div[@id='content']/form/select[3]//option[19]"));
        click(By.xpath("//div[@id='content']/form/select[4]//option[4]"));
        fillUpField(By.name("ayear"),contactData.getAyyear());

        if (creation) {
            click(By.xpath("//div[@id='content']/form/select[5]//option[2]"));
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        fillUpField(By.name("address2"),contactData.getAddress2());
        fillUpField(By.name("phone2"),contactData.getPhone2());
        fillUpField(By.name("notes"),contactData.getNotes2());
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
}
