package pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kshyniakov on 16.01.2017.
 */
public class GroupAddToContactTests extends TestBase{
    private ContactData editedContact;

    @BeforeMethod
    public void ensurePreconditions() {
        appManager.goTo().homePage();
        Contacts contacts = appManager.db().contacts();
        if (contacts.size()==0){
            editedContact = new ContactData().withFirstname("Edit").withLastname("Client");
            appManager.contact().create(editedContact);
        } else {
            Boolean creation = true;
            int groupsSize = appManager.db().groups().size();
            while (contacts.iterator().hasNext()){
                editedContact = contacts.iterator().next();
                if (editedContact.getGroups().size()<groupsSize){
                    creation = false;
                    break;
                }
            }
            if (creation) {
                editedContact = new ContactData().withFirstname("Edit").withLastname("Client");
                appManager.contact().create(editedContact);
            }
        }
        appManager.goTo().homePage();
    }

    @Test
    public void testGroupAddToContact() {

        Contacts before = appManager.db().contacts();
        Groups groups = appManager.db().groups();
        GroupData group = new GroupData();
        while (groups.iterator().hasNext()){
            group = groups.iterator().next();
            if (!editedContact.getGroups().contains(group)){
                appManager.contact().addGroupToContact(editedContact, group);
                break;
            }
        }
        Contacts after = appManager.db().contacts();

        assertThat(after, equalTo(before.without(editedContact).withAdded(editedContact.withGroup(group))));
    }
}
