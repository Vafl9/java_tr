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

    public void createContact(ContactData userData, boolean creation) {

        click(By.xpath(".//*[@id='nav']/ul/li[2]/a"));
        type(By.name("firstname"),userData.getName());
        type(By.name("lastname"),userData.getLastName());
        type(By.name("email"),userData.getMail());


        if(creation){ SelectByText(By.xpath(".//*[@id='content']/form/select[5]"),userData.getGroup());}
        else {
            Assert.assertFalse(isElementPresent(By.xpath(".//*[@id='content']/form/select[5]")));
        }

    }

    public void submitCreationContact()
    {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }


    public void deleteUser() {
        click(By.xpath(".//*[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editContact() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a"));
    }

    public void submitUpdateContact() {
        click(By.xpath(".//*[@id='content']/form[1]/input[22]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath(".//*[@name='selected[]']"));
    }

    public void selectContact() {
        click(By.xpath(".//*[@name='selected[]']"));
    }

    public void createNewContact(ContactData contactData, boolean b) {
        createContact(contactData,b);
        submitCreationContact();
    }
}
