package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void contactPage()
    {
        click(By.xpath(".//*[@id='nav']/ul/li[1]/a"));
    }

    public void groupPage() {
        if (!isElementPresent(By.tagName("h1")) && !GetElementText(By.tagName("h1")).equals("Groups") && !isElementPresent(By.tagName("new"))) {
            return;
        }
        click(By.linkText("groups"));


    }



}
