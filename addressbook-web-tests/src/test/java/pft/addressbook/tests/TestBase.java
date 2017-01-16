package pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pft.addressbook.appmanager.ApplicationManager;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;

import java.util.Comparator;

public class TestBase {

    protected static final ApplicationManager appManager
            = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        appManager.init();
    }

    @AfterSuite
    public void tearDown() {
        appManager.stop();
    }


    public void groupAvailability(){
        appManager.goTo().groupPage();
        if (appManager.db().groups().size()==0){
            appManager.group().create(new GroupData().withName("edit group").withHeader("test group header").withFooter("test footer"));
            appManager.group().returnToGroupPage();
        }
    }

    public void contactAvailability(){
        if (appManager.db().contacts().size()==0){
            Groups groups = appManager.db().groups();
            appManager.goTo().homePage();
            appManager.contact().create(new ContactData().withFirstname("Delete").withLastname("Client").withGroup(groups.iterator().next()));
            appManager.goTo().homePage();
        }
    }
}
