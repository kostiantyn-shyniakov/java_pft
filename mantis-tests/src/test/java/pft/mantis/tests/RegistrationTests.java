package pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by kshyniakov on 19.01.2017.
 */
public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        appManager.mail().start();
    }


    @Test(enabled = false)
    public void testRegistration() throws IOException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = "user" + now;
        String password = "password";
        appManager.registration().start(user, email, "/signup_page.php");
        List<MailMessage> mailMessages = appManager.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        appManager.registration().finish(confirmationLink, password);
        assertTrue(appManager.newSession().login(user, password));
    }

    @Test
    public void testChangePassword() throws IOException {
        String adminLogin = appManager.getProperty("web.adminLogin");
        String password = appManager.getProperty("web.adminPassword");
        String newPassword = "54321";
        appManager.registration().start(adminLogin, password, "/login_page.php");
        String email = appManager.registration().resetUserPassword();
        String user = email.split("@")[0];
        List<MailMessage> mailMessages = appManager.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        appManager.registration().finish(confirmationLink, newPassword);
        assertTrue(appManager.newSession().login(user, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        appManager.mail().stop();
    }
}
