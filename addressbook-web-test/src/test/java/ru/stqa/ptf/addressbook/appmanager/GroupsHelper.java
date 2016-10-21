package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.ptf.addressbook.model.GroupDate;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupsHelper extends HelperBase {


    public GroupsHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupDate groupDate) {
        type(By.name("group_name"), groupDate.getName());
        type(By.name("group_header"), groupDate.getHeader());
        type(By.name("group_footer"), groupDate.getFooter());

    }

    public void InitGroupCreation() {
        click(By.name("new"));
    }


    public void create(GroupDate groupDate) {
        InitGroupCreation();
        fillGroupForm(groupDate);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupDate group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }


    public void delete(GroupDate deletedGroup) {
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
            groupCache.add(new GroupDate().withName(name).withId(id));
        }

        return new Groups(groupCache);

    }

}
