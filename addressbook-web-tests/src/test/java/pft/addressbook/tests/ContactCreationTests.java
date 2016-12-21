package pft.addressbook.tests;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testAddNew() {
        appManager.goTo().homePage();
        Contacts before = appManager.contact().all();
        ContactData contact = new ContactData().withFirstname("SecondAdded").withLastname("UClient");
        appManager.contact().create(contact);
        Contacts after = appManager.contact().all();

        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
