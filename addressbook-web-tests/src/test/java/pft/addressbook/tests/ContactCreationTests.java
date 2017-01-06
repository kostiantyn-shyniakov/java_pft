package pft.addressbook.tests;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testAddNew() {
        appManager.goTo().homePage();
        Contacts before = appManager.contact().all();
        File photo = new File("src/test/resources/stru_clean.gif");
        ContactData contact = new ContactData().withFirstname("SecondAdded").withLastname("UClient").withPhoto(photo);
        appManager.contact().create(contact);
        Contacts after = appManager.contact().all();

        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
