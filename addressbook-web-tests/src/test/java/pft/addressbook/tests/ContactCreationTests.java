package pft.addressbook.tests;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testAddNew() {
        applicationManager.getContactHelper().createNewContact(new ContactData("First", "F", "Client", "Nick", "Title", "Roga&Copyta", "Bakery str.", "745-69-34", "nothing@gmail.com", "google.com", "1980", "1990", "Second place Avenue", "SweetHome", "nothing"));
        applicationManager.getContactHelper().returnAddressbookPage();
    }

}
