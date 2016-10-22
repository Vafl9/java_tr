package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void createContact() {
        click(By.xpath(".//*[@id='nav']/ul/li[2]/a"));
    }

    public void fillFormContact(ContactData contactData, boolean creation) {

        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("email"), contactData.getEmail());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        //attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                SelectByText(By.xpath(".//*[@id='content']/form/select[5]"), contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.xpath(".//*[@id='content']/form/select[5]")));
        }

    }

    public void submitCreation() {
        click(By.xpath(".//*[@id='content']/form[1]/input[1]"));
    }


    public void delete(ContactData contact) {
        selectContact(contact.getId());
        wd.findElement(By.xpath(".//*[@id='content']/form[2]/div[2]/input")).click();
        wd.switchTo().alert().accept();
        contactCache = null;
        returnToContactPage();
    }


    public void submitUpdateContact() {
        click(By.xpath(".//*[@id='content']/form[1]/input[22]"));
    }


    public void modify(ContactData contact) {
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

    public void createNewContact(ContactData contactData, boolean b) {
        createContact();
        fillFormContact(contactData, b);
        submitCreation();
        contactCache = null;
        returnToContactPage();

    }

    public void selectModifyContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void selectInformationContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
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
            String allMailPath = ".//*[@id='maintable']/tbody/tr[%s]/td[5]";
            String addressPath = ".//*[@id='maintable']/tbody/tr[%s]/td[4]";


            int id = Integer.parseInt(element.getAttribute("value"));


            String lastName = wd.findElement(By.xpath(String.format(lastNamePath, index))).getText();
            String name = wd.findElement(By.xpath(String.format(NamePath, index))).getText();
            String allPhones = wd.findElement(By.xpath(String.format(allPhonePath, index))).getText();
            String address = wd.findElement(By.xpath(String.format(addressPath, index))).getText();
            String allMail = wd.findElement(By.xpath(String.format(allMailPath, index))).getText();


            String allInformation = name + lastName + address + allPhones + allMail;

            contactCache.add(new ContactData().withName(name).withLastName(lastName).withId(id).withAllPhones(allPhones).withAllMail(allMail).withAddress(address).withAllContactInformation(allInformation));

            index++;
        }

        return new Contacts(contactCache);

    }


    public ContactData infoFromEditForm(ContactData contact) {
        selectModifyContactById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstName).withLastName(lastName).
                withHome(home).withMobile(mobile).withWork(work).withEmail(email).withSecondEmail(secondEmail).withAddress(address);

    }

    public ContactData infoFromInformationForm(ContactData contact) {
        selectInformationContactById(contact.getId());
        String contactInformation = wd.findElement(By.cssSelector("#content")).getText();
        wd.navigate().back();
        return new ContactData().withAllContactInformation(contactInformation);
    }
}
