package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.ptf.addressbook.model.GroupDate;

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



    public void deleteCreationGroup() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initGroupModification() {
        click(By.xpath(".//*[@id='content']/form/input[3]"));
    }

    public void submitGroupModification() {
        click(By.xpath(".//*[@id='content']/form/input[3]"));
    }
}
