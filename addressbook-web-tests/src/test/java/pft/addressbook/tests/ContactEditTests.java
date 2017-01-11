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
public class ContactEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (appManager.db().contacts().size()==0){
            appManager.goTo().homePage();
            appManager.contact().create(new ContactData().withFirstname("Edit").withLastname("Client"));
            appManager.goTo().homePage();
        }
    }

    @Test
    public void testEditContact() {

        Contacts before = appManager.db().contacts();
        ContactData editedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(editedContact.getId()).withFirstname("EditUp").withLastname("EditedNext").withAddress("Simple address");
        appManager.contact().editContact(contact);
        Contacts after = appManager.db().contacts();

        assertThat(after, equalTo(before.without(editedContact).withAdded(contact)));
    }
}
