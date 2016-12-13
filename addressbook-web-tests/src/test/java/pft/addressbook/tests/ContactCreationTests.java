package pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testAddNew() {
        applicationManager.getNavigationHelper().goToHomePage();
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        ContactData contact = new ContactData("SecondAdded", "UClient");
        applicationManager.getContactHelper().createNewContact(contact);
        List<ContactData> after = applicationManager.getContactHelper().getContactList();

        contact.setId(after.stream().max(byContactDataId).get().getId());
        before.add(contact);
        before.sort(byContactDataId);
        after.sort(byContactDataId);
        Assert.assertEquals(after, before);
    }

}
