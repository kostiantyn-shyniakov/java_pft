package pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class TestBase {

    protected final ApplicationManager applicationManager = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeMethod
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterMethod
    public void tearDown() {
        applicationManager.stop();
    }

}
