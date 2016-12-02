package pft.addressbook.tests;

import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class GroupEditTests extends TestBase {

    @Test
    public void testEditGroup(){
        applicationManager.getNavigationHelper().gotoGroupPage();
        if (!applicationManager.getGroupHelper().isGroupPresent()){
            applicationManager.getGroupHelper().createNewGroup(new GroupData("edit group", "test group header", "test footer"));
            applicationManager.getGroupHelper().returnToGroupPage();
        }
        applicationManager.getGroupHelper().editGroup(new GroupData("new test group", "new test group header", "new test footer"));
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
