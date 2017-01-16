package pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        groupAvailability();
    }

    @Test(enabled = false)
    public void testDeletionGroup(){

        Groups before = appManager.db().groups();
        GroupData deletedGroup = before.iterator().next();
        appManager.group().deleteGroup(deletedGroup);
        appManager.group().returnToGroupPage();
        Groups after = appManager.db().groups();

        assertThat(after, equalTo(before.without(deletedGroup)));
    }

    @Test
    public void testDeletionGroupFromContact(){
        contactAvailability();
        Contacts before = appManager.db().contacts();
        ContactData deletedContact = before.iterator().next();
        GroupData deletedGroup = deletedContact.getGroups().iterator().next();
        appManager.goTo().homePage();
        appManager.contact().deleteGroupFromContact(deletedContact, deletedGroup);
        //Thread.sleep(100);
        Contacts after = appManager.db().contacts();

        assertThat(after, equalTo(before.without(deletedContact).withAdded(deletedContact.withoutGroup(deletedGroup))));
    }
}
