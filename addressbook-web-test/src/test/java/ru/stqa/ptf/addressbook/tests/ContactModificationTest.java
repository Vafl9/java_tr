package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.Set;

public class ContactModificationTest extends TestBase {


    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().createNewContact(new ContactDate().withName("Andrew").withLastName("Dzhodzhua").withMail("Head@mail.ru").withGroup("Test1"), true);
        }
    }


    @Test
    public void ContactModificationTest() {

        Set<ContactDate> before = app.contact().all();
        ContactDate modifiedContact = before.iterator().next();
        ContactDate contact = new ContactDate().
                withName("Dzhodzhua").withLastName("Dzhodzhua").withMail("Head@mail.ru").withGroup("Test1").withId(modifiedContact.getId());
        app.contact().modify(contact);
        Set<ContactDate> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(before, after);


    }


}
