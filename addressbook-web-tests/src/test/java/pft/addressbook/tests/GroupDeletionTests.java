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
public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        appManager.goTo().groupPage();
        if (appManager.group().all().size()==0){
            appManager.group().create(new GroupData().withName("delete group").withHeader("test header").withFooter("test footer"));
            appManager.group().returnToGroupPage();
        }
    }

    @Test
    public void testDeletionGroup(){

        Groups before = appManager.group().all();
        GroupData deletedGroup = before.iterator().next();
        appManager.group().deleteGroup(deletedGroup);
        appManager.group().returnToGroupPage();
        Groups after = appManager.group().all();

        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
