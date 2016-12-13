package pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        GroupData group = new GroupData(before.get(before.size()-1).getId(), "edit test group", "new test group header", "new test footer");
        applicationManager.getGroupHelper().editGroup(before.size()-1,group);
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
        before.remove(before.size()-1);
        before.add(group);
        before.sort(byGroupDataId);
        after.sort(byGroupDataId);
        Assert.assertEquals(after,before);
    }
}
