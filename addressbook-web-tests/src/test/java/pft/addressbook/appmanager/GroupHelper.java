package pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kshyniakov on 28.11.2016.
 */
public class GroupHelper extends BaseHelper{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void createNewGroup(GroupData groupData) {
        click(By.name("new"));
        fillGroupForm(groupData);
        click(By.name("submit"));
    }

    public void deleteGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        click(By.xpath("//*[@id='content']/form/input[2]"));
    }

    public void editGroup(int index, GroupData groupData) {

        wd.findElements(By.name("selected[]")).get(index).click();
        click(By.xpath("//*[@id='content']/form/input[3]"));
        fillGroupForm(groupData);
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData groupData) {
        fillUpField(By.name("group_name"), groupData.getGroupName());
        fillUpField(By.name("group_header"), groupData.getGroupHeader());
        fillUpField(By.name("group_footer"), groupData.getGroupFooter());
    }

    public boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
        for(WebElement e : elements){
            String name = e.getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
