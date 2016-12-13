package pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class ContactEditTests extends TestBase {

    @Test
    public void testEditContact(){
        applicationManager.getNavigationHelper().goToHomePage();
        if (!applicationManager.getContactHelper().isContactPresent()){
            applicationManager.getContactHelper().createNewContact(new ContactData("Create", "Client"));
            applicationManager.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        ContactData contact = new ContactData(before.get(0).getId(), "EditUp", "EditedNext");
        applicationManager.getContactHelper().editContact(contact);
        before.remove(0);
        before.add(contact);
        List<ContactData> after = applicationManager.getContactHelper().getContactList();
        before.sort(byContactDataId);
        after.sort(byContactDataId);
        Assert.assertEquals(after,before);
    }
}
