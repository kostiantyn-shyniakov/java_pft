package pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void create(GroupData groupData) {
        click(By.name("new"));
        fillGroupForm(groupData);
        click(By.name("submit"));
        groupCache = null;
    }

    public void deleteGroup(GroupData group) {
        wd.findElement(By.cssSelector("input[value='"+group.getId()+"']")).click();
        click(By.xpath("//*[@id='content']/form/input[2]"));
        groupCache = null;
    }

    public void edit(GroupData group) {
        wd.findElement(By.cssSelector("input[value='"+group.getId()+"']")).click();
        click(By.xpath("//*[@id='content']/form/input[3]"));
        fillGroupForm(group);
        click(By.name("update"));
        groupCache = null;
    }

    private void fillGroupForm(GroupData groupData) {
        fillUpField(By.name("group_name"), groupData.getGroupName());
        fillUpField(By.name("group_header"), groupData.getGroupHeader());
        fillUpField(By.name("group_footer"), groupData.getGroupFooter());
    }

    public boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) return new Groups(groupCache);
        groupCache = new Groups();
        List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
        for(WebElement e : elements){
            String name = e.getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }
}
