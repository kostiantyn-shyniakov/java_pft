package pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by kshyniakov on 30.11.2016.
 */
public class GroupEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        groupAvailability();
    }

    @Test
    public void testEditGroup(){

        Groups before = appManager.db().groups();
        GroupData editedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(editedGroup.getId()).withName("edit group").withHeader("new header").withFooter("new footer");
        appManager.group().edit(group);
        appManager.group().returnToGroupPage();
        Groups after = appManager.db().groups();

        assertThat(after, equalTo(before.without(editedGroup).withAdded(group)));
    }
}
