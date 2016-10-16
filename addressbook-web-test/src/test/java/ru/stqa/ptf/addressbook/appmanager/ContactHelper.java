package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.ptf.addressbook.model.UserData;

public class ContactHelper extends HelperBase {
    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void createUser(UserData userData) {

        click(By.linkText("add new"));
        type(By.name("firstname"),userData.getName());
        type(By.name("lastname"),userData.getLastName());
        type(By.name("email"),userData.getMail());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }



}
