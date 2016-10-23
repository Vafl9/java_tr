package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.List;

public class GroupsHelper extends HelperBase {


    public GroupsHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {

        click(By.xpath(".//*[@id='content']/form/input[2]"));
        //click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());

    }

    public void InitGroupCreation() {
        click(By.name("new"));
    }


    public void create(GroupData groupData) {
        InitGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }


    public void delete(GroupData deletedGroup) {
        selectGroupById(deletedGroup.getId());
        deleteCreationGroup();
        groupCache = null;
        returnToGroupPage();
    }

    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteCreationGroup() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void initGroupModification() {
        click(By.xpath(".//*[@id='content']/form/input[3]"));
    }

    public void submitGroupModification() {
        click(By.xpath(".//*[@id='content']/form/input[3]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.xpath(".//*[@name='selected[]']")).size();
    }

    private  Groups groupCache = null;

    public Groups all() {

        if (groupCache != null)
        {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            groupCache.add(new GroupData().withName(name).withId(id));
        }

        return new Groups(groupCache);

    }

}
