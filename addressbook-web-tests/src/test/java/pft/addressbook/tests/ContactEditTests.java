package pft.addressbook.tests;

import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class ContactEditTests extends TestBase {

    @Test
    public void testEditContact(){
        applicationManager.getNavigationHelper().goToHomePage();
        applicationManager.getContactHelper().editContact(new ContactData("Fifth", "F", "Clientius", "Nick", "Title", "Roga&Copyta", "Bakery str.", "745-69-34", "nothing@gmail.com", "google.com", "1980", "1990", "Second place Avenue", "SweetHome", "nothing"));
    }
}
