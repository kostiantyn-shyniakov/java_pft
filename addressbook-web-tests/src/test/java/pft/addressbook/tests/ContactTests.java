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
    public void testContactEditPageInfo(){

        ContactData contact = appManager.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = appManager.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    @Test
    public void testContactDetailedInfo(){
        ContactData contact = appManager.contact().all().iterator().next();
        ContactData contactDetailedInfor = appManager.contact().detailedInfoForm(contact);
        ContactData contactInfoFromEditForm = appManager.contact().infoFromEditForm(contact);
        assertThat(contactDetailedInfor.getDetailedInfo(), equalTo(mergeDetailedInfo(contactInfoFromEditForm)));
    }

    private String mergeDetailedInfo(ContactData contact) {
        return contact.getFirstname()+" "+contact.getLastname()+"\n"+contact.getAddress()+"\n\n"
                +"H: "+contact.getHomePhone()+"\n"+"M: "+contact.getMobilePhone()+"\n"+"W: "
                +contact.getWorkPhone()+"\n\n"+mergeEmails(contact);
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
