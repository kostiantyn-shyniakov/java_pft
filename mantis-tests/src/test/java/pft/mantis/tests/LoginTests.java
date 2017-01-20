package pft.mantis.tests;

import org.testng.annotations.Test;
import pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by kshyniakov on 19.01.2017.
 */
public class LoginTests extends TestBase{

    @Test
    public void testLogin() throws IOException {
        HttpSession session = appManager.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
