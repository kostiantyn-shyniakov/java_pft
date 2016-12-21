package pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by kshyniakov on 21.12.2016.
 */
public class ContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        appManager.goTo().homePage();
        if (appManager.contact().all().size()==0){
            appManager.contact().create(new ContactData().withFirstname("testPhone").withLastname("Client"));
            appManager.goTo().homePage();
        }
    }

    @Test
    public void testContact(){

        ContactData contact = appManager.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = appManager.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactTests::clean)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactTests::clean)
                .collect(Collectors.joining("\n"));
    }

    public static String clean(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
