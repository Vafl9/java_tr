package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactDate;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void createContact() {
        click(By.xpath(".//*[@id='nav']/ul/li[2]/a"));
    }

    public void fillFormContact(ContactDate userData, boolean creation) {

        type(By.name("firstname"), userData.getName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("email"), userData.getMail());


        if (creation) {
            SelectByText(By.xpath(".//*[@id='content']/form/select[5]"), userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.xpath(".//*[@id='content']/form/select[5]")));
        }

    }

    public void submitCreation() {
        click(By.xpath(".//*[@id='content']/form[1]/input[1]"));
    }


    public void delete(ContactDate contact) {
        selectContact(contact.getId());
        wd.findElement(By.xpath(".//*[@id='content']/form[2]/div[2]/input")).click();
        wd.switchTo().alert().accept();
        contactCache = null;
        returnToContactPage();
    }


    public void submitUpdateContact() {
        click(By.xpath(".//*[@id='content']/form[1]/input[22]"));
    }


    public void modify(ContactDate contact) {
        selectModifyContactById(contact.getId());
        fillFormContact(contact, false);
        submitUpdateContact();
        contactCache = null;
        returnToContactPage();
    }

    public void returnToContactPage() {
        click(By.xpath(".//*[@id='nav']/ul/li[1]/a"));
    }

    public void selectContact(int i) {

        wd.findElement(By.xpath(String.format(".//*[@id='%s']", i))).click();
    }

    public void createNewContact(ContactDate contactData, boolean b) {
        createContact();
        fillFormContact(contactData, b);
        submitCreation();
        contactCache = null;
        returnToContactPage();

    }

    public void selectModifyContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    private Contacts contactCache = null;

    public int getContactCount() {
        return wd.findElements(By.xpath(".//td[@class='center']/*[@name ='selected[]']")).size();
    }

    public Contacts all() {

        int index = 2;

        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();

        List<WebElement> elements = wd.findElements(By.xpath(".//td[@class='center']/*[@name ='selected[]']"));

        for (WebElement element : elements) {
            String lastNamePath = ".//*[@id='maintable']/tbody/tr[%s]/td[2]";
            String NamePath = ".//*[@id='maintable']/tbody/tr[%s]/td[3]";
            String allPhonePath = ".//*[@id='maintable']/tbody/tr[%s]/td[6]";


            int id = Integer.parseInt(element.getAttribute("value"));


            String lastName = wd.findElement(By.xpath(String.format(lastNamePath, index))).getText();
            String name = wd.findElement(By.xpath(String.format(NamePath, index))).getText();
            String[] phones = wd.findElement(By.xpath(String.format(allPhonePath, index))).getText().split("\n");


            contactCache.add(new ContactDate().withName(name).withLastName(lastName).withId(id).withHome(phones[0]).withMobile(phones[1]).withWork(phones[2]));

            index++;
        }

        return new Contacts(contactCache);

    }

    public ContactDate infoFromEditForm(ContactDate contact) {
        selectModifyContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactDate().withId(contact.getId()).withName(firstname).withLastName(lastname).withHome(home).withMobile(mobile).withWork(work);

    }
}
