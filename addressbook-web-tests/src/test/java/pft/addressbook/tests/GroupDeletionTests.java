package pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class GroupDeletionTests extends TestBase {

    @Test
    public void testDeletionGroup(){
        applicationManager.getNavigationHelper().gotoGroupPage();
        applicationManager.getGroupHelper().deleteGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
