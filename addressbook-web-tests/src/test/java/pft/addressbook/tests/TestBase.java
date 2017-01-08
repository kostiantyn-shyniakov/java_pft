package pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pft.addressbook.appmanager.ApplicationManager;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.GroupData;

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

}
