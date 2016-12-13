package pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pft.addressbook.appmanager.ApplicationManager;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.GroupData;

import java.util.Comparator;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class TestBase {

    protected final ApplicationManager applicationManager = new ApplicationManager(BrowserType.FIREFOX);
    protected Comparator<? super GroupData> byGroupDataId = (o1, o2) -> Integer.compare(o1.getId(),o2.getId());
    protected Comparator<? super ContactData> byContactDataId = (o1, o2) -> Integer.compare(o1.getId(),o2.getId());

    @BeforeMethod
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterMethod
    public void tearDown() {
        applicationManager.stop();
    }

}
