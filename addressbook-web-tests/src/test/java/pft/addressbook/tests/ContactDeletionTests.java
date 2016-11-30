package pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        applicationManager.getNavigationHelper().goToHomePage();
        applicationManager.getContactHelper().deleteContact();
    }
}
