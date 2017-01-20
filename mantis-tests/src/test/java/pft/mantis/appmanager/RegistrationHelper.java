package pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by kshyniakov on 19.01.2017.
 */
public class RegistrationHelper extends BaseHelper{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
        fillUpField(By.name("username"), username);
        fillUpField(By.name("email"), email);
        click(By.cssSelector("input.button"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        fillUpField(By.name("password"), password);
        fillUpField(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
