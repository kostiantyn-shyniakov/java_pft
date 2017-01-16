package pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        contactAvailability();
    }

    @Test
    public void testContactDeletion() throws InterruptedException {

        Contacts before = appManager.db().contacts();
        ContactData deletedContact = before.iterator().next();
        appManager.contact().deleteContact(deletedContact);
        Thread.sleep(100);
        Contacts after = appManager.db().contacts();

        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
