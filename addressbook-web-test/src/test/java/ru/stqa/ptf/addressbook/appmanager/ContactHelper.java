package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void createUser(ContactData userData, boolean creation) {

        click(By.linkText("add new"));
        type(By.name("firstname"),userData.getName());
        type(By.name("lastname"),userData.getLastName());
        type(By.name("email"),userData.getMail());


        if(creation){ new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());}
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitCreationUser()
    {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }


    public void deleteUser() {
        click(By.xpath(".//*[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void selectUser() {
        click(By.xpath(".//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitUpdateUser() {
        click(By.xpath(".//*[@id='content']/form[1]/input[22]"));
    }
}
