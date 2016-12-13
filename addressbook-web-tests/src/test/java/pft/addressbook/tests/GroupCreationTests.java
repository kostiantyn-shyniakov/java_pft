package pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        applicationManager.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test group", "test group header", "test footer");
        applicationManager.getGroupHelper().createNewGroup(group);
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();

        group.setId(after.stream().max(byGroupDataId).get().getId());
        before.add(group);
        before.sort(byGroupDataId);
        after.sort(byGroupDataId);
        Assert.assertEquals(after, before);
    }

}
