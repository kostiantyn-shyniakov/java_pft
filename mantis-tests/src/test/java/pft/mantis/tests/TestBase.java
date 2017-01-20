package pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager appManager
            = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        appManager.init();
        appManager.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
        appManager.ftp().restore("config_inc.php.bak", "config_inc.php");
        appManager.stop();
    }

}
