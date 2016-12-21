package pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pft.addressbook.appmanager.ApplicationManager;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.GroupData;

import java.util.Comparator;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class TestBase {

    protected static final ApplicationManager appManager = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite
    public void setUp() throws Exception {
        appManager.init();
    }

    @AfterSuite
    public void tearDown() {
        appManager.stop();
    }

}
