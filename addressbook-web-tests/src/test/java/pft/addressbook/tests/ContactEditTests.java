package pft.addressbook.tests;

import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class ContactEditTests extends TestBase {

    @Test
    public void testEditContact(){
        appManager.goTo().homePage();
        if (appManager.contact().all().size()==0){
            appManager.contact().create(new ContactData().withFirstname("Create").withLastname("Client"));
            appManager.goTo().homePage();
        }

        Contacts before = appManager.contact().all();
        ContactData editedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(editedContact.getId()).withFirstname("EditUp").withLastname("EditedNext");
        appManager.contact().editContact(contact);
        Contacts after = appManager.contact().all();

        assertThat(after, equalTo(before.without(editedContact).withAdded(contact)));
    }
}
