package pft.addressbook.tests;

import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        applicationManager.getNavigationHelper().gotoGroupPage();
        applicationManager.getGroupHelper().createNewGroup(new GroupData("test group", "test group header", "test footer"));
        applicationManager.getGroupHelper().returnToGroupPage();
    }

}
