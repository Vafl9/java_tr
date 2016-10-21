package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void createContact()
    {
        click(By.xpath(".//*[@id='nav']/ul/li[2]/a"));
    }

    public void fillFormContact(ContactDate userData, boolean creation) {

        type(By.name("firstname"),userData.getName());
        type(By.name("lastname"),userData.getLastName());
        type(By.name("email"),userData.getMail());


        if(creation){ SelectByText(By.xpath(".//*[@id='content']/form/select[5]"),userData.getGroup());}
        else {
            Assert.assertFalse(isElementPresent(By.xpath(".//*[@id='content']/form/select[5]")));
        }

    }

    public void submitCreation()
    {
        click(By.xpath(".//*[@id='content']/form[1]/input[1]"));
    }


    public void delete(ContactDate contact) {
        selectContact(contact.getId());
        wd.findElement(By.xpath(".//*[@id='content']/form[2]/div[2]/input")).click();
        wd.switchTo().alert().accept();
    }

    public void selectContactById(String path) {
        System.out.println(path);
        wd.findElement(By.xpath(path)).click();
    }

    public void submitUpdateContact() {
        click(By.xpath(".//*[@id='content']/form[1]/input[22]"));

    }


    public void modify(ContactDate contact) {
        selectContactById(contact.getEditButtonXPath());
        fillFormContact(contact,false);
        submitUpdateContact();
        returnToContactPage();
    }

    public void returnToContactPage()
    {
        click(By.xpath(".//*[@id='nav']/ul/li[1]/a"));
    }

    public void selectContact(int i) {

        wd.findElement(By.xpath(String.format(".//*[@id='%s']",i))).click();
    }

    public void createNewContact(ContactDate contactData, boolean b) {
        createContact();
        fillFormContact(contactData,b);
        submitCreation();
        click(By.xpath(".//*[@id='nav']/ul/li[1]/a"));

    }



    public Set<ContactDate> all() {

        int index = 2;



        Set<ContactDate> contacts = new HashSet<>();

        List<WebElement> elements = wd.findElements(By.xpath(".//td[@class='center']/*[@name ='selected[]']"));

        for (WebElement element : elements)
        {
            String lastNamePath = ".//*[@id='maintable']/tbody/tr[%s]/td[2]";
            String NamePath = ".//*[@id='maintable']/tbody/tr[%s]/td[3]";
            String editButton = ".//*[@id='maintable']/tbody/tr[%s]/td[8]";

            String editButtonPath = String.format(editButton,index);

            int id = Integer.parseInt(element.getAttribute("value"));


            String lastName = wd.findElement(By.xpath(String.format(lastNamePath,index))).getText();
            String name = wd.findElement(By.xpath(String.format(NamePath,index))).getText();


            contacts.add(new ContactDate().withName(name).withLastName(lastName).withId(id).withEditButton(editButtonPath));

            index++;
        }

        return contacts;

    }
}
