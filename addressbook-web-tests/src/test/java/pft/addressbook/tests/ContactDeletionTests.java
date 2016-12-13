package pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        applicationManager.getNavigationHelper().goToHomePage();
        if (!applicationManager.getContactHelper().isContactPresent()){
            applicationManager.getContactHelper().createNewContact(new ContactData("Delete", "Client"));
            applicationManager.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        applicationManager.getContactHelper().deleteContact();
        before.remove(0);
        List<ContactData> after = applicationManager.getContactHelper().getContactList();
        Assert.assertEquals(after,before);
    }
}
