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
        appManager.goTo().homePage();
        if (appManager.contact().all().size()==0){
            appManager.contact().create(new ContactData().withFirstname("Delete").withLastname("Client"));
            appManager.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion(){

        Contacts before = appManager.contact().all();
        ContactData deletedContact = before.iterator().next();
        appManager.contact().deleteContact(deletedContact);
        Contacts after = appManager.contact().all();

        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
