package pft.addressbook.tests;

import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        appManager.goTo().groupPage();
        Groups before = appManager.group().all();
        GroupData group = new GroupData().withName("test group").withHeader("test header").withFooter("test footer");
        appManager.group().create(group);
        appManager.group().returnToGroupPage();
        Groups after = appManager.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
