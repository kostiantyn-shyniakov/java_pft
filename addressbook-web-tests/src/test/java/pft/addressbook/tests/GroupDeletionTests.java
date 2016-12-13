package pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;

import java.util.List;

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
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();

        applicationManager.getGroupHelper().deleteGroup(before.size()-1);
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();

        before.remove(before.size()-1);
        Assert.assertEquals(after,before);
    }
}
