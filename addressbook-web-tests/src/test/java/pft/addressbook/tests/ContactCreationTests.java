package pft.addressbook.tests;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("C:/Users/kshyniakov/Documents/GitHub/java_pft/addressbook-web-tests/src/test/resources/contacts.json")));
        String json="";
        String line = reader.readLine();
        while (line != null) {
            json +=line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());//List<GroupData>.class
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void ensurePreconditions(){
        groupAvailability();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testAddNew(ContactData contact) {
        Groups groups = appManager.db().groups();
        Contacts before = appManager.db().contacts();
        appManager.goTo().homePage();
        appManager.contact().create(contact.withGroup(groups.iterator().next()));
        Contacts after = appManager.db().contacts();
        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
