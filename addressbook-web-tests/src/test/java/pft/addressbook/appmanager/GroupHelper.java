package pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import pft.addressbook.model.GroupData;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class GroupHelper extends BaseHelper{

    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void createNewGroup(GroupData groupData) {
        click(By.name("new"));
        fillUpField(By.name("group_name"), groupData.getGroupName());
        fillUpField(By.name("group_header"), groupData.getGroupHeader());
        fillUpField(By.name("group_footer"), groupData.getGroupFooter());
        click(By.name("submit"));
    }

    public void deleteGroup() {
        if (!isSelected(By.name("selected[]"))) {
            click(By.name("selected[]"));
        }
        click(By.xpath("//*[@id='content']/form/input[2]"));
    }

    public void editGroup(GroupData groupData) {
        if (!isSelected(By.name("selected[]"))) {
            click(By.name("selected[]"));
        }
        click(By.xpath("//*[@id='content']/form/input[3]"));
        fillUpField(By.name("group_name"), groupData.getGroupName());
        fillUpField(By.name("group_header"), groupData.getGroupHeader());
        fillUpField(By.name("group_footer"), groupData.getGroupFooter());
        click(By.name("update"));
    }
}
