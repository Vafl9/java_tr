package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage() {
        if(isElementPresent(By.id("maintable")))
        {
            return;
        }
        click(By.linkText("home"));
    }

    public void gotoGroupPage() {
        if (!isElementPresent(By.tagName("h1")) && !GetElementText(By.tagName("h1")).equals("Groups") && !isElementPresent(By.tagName("new"))) {
            return;
        }
        click(By.linkText("groups"));


    }



}
