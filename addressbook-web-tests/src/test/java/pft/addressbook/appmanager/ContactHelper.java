package pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pft.addressbook.model.ContactData;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnAddressbookPage() {
        click(By.cssSelector("th.sortable.fd-column-2"));
    }

    public void createNewContact(ContactData addNewData) {
        click(By.linkText("add new"));
        fillUpField(By.name("firstname"),addNewData.getFirstname());
        fillUpField(By.name("middlename"),addNewData.getMiddlename());
        fillUpField(By.name("lastname"),addNewData.getLastname());
        fillUpField(By.name("nickname"),addNewData.getNickname());
        fillUpField(By.name("title"),addNewData.getTitle());
        fillUpField(By.name("company"),addNewData.getCompany());
        fillUpField(By.name("address"),addNewData.getAddress());
        click(By.name("theform"));
        fillUpField(By.name("home"),addNewData.getHomephone());
        click(By.name("mobile"));
        fillUpField(By.name("email"),addNewData.getEmail());
        fillUpField(By.name("homepage"),addNewData.getHomepage());

        if (!isSelected(By.xpath("//div[@id='content']/form/select[1]//option[18]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[18]"));
        }
        if (!isSelected(By.xpath("//div[@id='content']/form/select[2]//option[10]"))) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[10]"));
        }
        fillUpField(By.name("byear"),addNewData.getBirthyear());

        if (!isSelected(By.xpath("//div[@id='content']/form/select[3]//option[19]"))) {
            click(By.xpath("//div[@id='content']/form/select[3]//option[19]"));
        }
        if (!isSelected(By.xpath("//div[@id='content']/form/select[4]//option[4]"))) {
            click(By.xpath("//div[@id='content']/form/select[4]//option[4]"));
        }
        fillUpField(By.name("ayear"),addNewData.getAyyear());
        if (!isSelected(By.xpath("//div[@id='content']/form/select[5]//option[2]"))) {
            click(By.xpath("//div[@id='content']/form/select[5]//option[2]"));
        }
        click(By.name("theform"));
        fillUpField(By.name("address2"),addNewData.getAddress2());
        fillUpField(By.name("phone2"),addNewData.getPhone2());
        fillUpField(By.name("notes"),addNewData.getNotes2());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void deleteContact() {
        if (!isSelected(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]/input"))) {
            click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]/input"));
        }
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editContact(ContactData editContactData) {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
        fillUpField(By.name("firstname"),editContactData.getFirstname());
        fillUpField(By.name("middlename"),editContactData.getMiddlename());
        fillUpField(By.name("lastname"),editContactData.getLastname());
        fillUpField(By.name("nickname"),editContactData.getNickname());
        fillUpField(By.name("title"),editContactData.getTitle());
        fillUpField(By.name("company"),editContactData.getCompany());
        fillUpField(By.name("address"),editContactData.getAddress());
        fillUpField(By.name("home"),editContactData.getHomephone());
        fillUpField(By.name("email"),editContactData.getEmail());
        fillUpField(By.name("homepage"),editContactData.getHomepage());

        if (!isSelected(By.xpath("//div[@id='content']/form/select[1]//option[18]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[18]"));
        }
        if (!isSelected(By.xpath("//div[@id='content']/form/select[2]//option[10]"))) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[10]"));
        }
        fillUpField(By.name("byear"),editContactData.getBirthyear());

        if (!isSelected(By.xpath("//div[@id='content']/form/select[3]//option[19]"))) {
            click(By.xpath("//div[@id='content']/form/select[3]//option[19]"));
        }
        if (!isSelected(By.xpath("//div[@id='content']/form/select[4]//option[4]"))) {
            click(By.xpath("//div[@id='content']/form/select[4]//option[4]"));
        }
        fillUpField(By.name("ayear"),editContactData.getAyyear());
        fillUpField(By.name("address2"),editContactData.getAddress2());
        fillUpField(By.name("phone2"),editContactData.getPhone2());
        fillUpField(By.name("notes"),editContactData.getNotes2());
        click(By.xpath("//*[@id='content']/form[1]/input[1]"));
    }
}
