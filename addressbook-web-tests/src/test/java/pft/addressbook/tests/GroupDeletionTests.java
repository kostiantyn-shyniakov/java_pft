package pft.addressbook.tests;

import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class GroupDeletionTests extends TestBase {

    @Test
    public void testDeletionGroup(){
        applicationManager.getNavigationHelper().gotoGroupPage();
        if (!applicationManager.getGroupHelper().isGroupPresent()){
            applicationManager.getGroupHelper().createNewGroup(new GroupData("new group", "test group header", "test footer"));
            applicationManager.getGroupHelper().returnToGroupPage();
        }
        applicationManager.getGroupHelper().deleteGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
