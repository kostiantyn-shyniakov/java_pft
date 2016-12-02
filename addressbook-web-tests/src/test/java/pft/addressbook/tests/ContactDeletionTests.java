package pft.addressbook.tests;

import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        applicationManager.getNavigationHelper().goToHomePage();
        if (!applicationManager.getContactHelper().isContactPresent()){
            applicationManager.getContactHelper().createNewContact(new ContactData("Delete", "F", "Client", "Nick", "Title", "Roga&Copyta", "Bakery str.", "745-69-34", "nothing@gmail.com", "google.com", "1980", "1990", "Second place Avenue", "SweetHome", "nothing"));
            applicationManager.getNavigationHelper().goToHomePage();
        }
        applicationManager.getContactHelper().deleteContact();
    }
}
