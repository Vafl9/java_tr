package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.Set;

public class ContactDeletionTest extends TestBase {


    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().createNewContact(new ContactDate().withName("Andrew").withLastName("Dzhodzhua").withMail("Head@mail.ru").withGroup("Test1"), true);
        }
    }


    @Test
    public void ContactDeletionTest(){
        Set<ContactDate> before = app.contact().all();
        ContactDate modifiedContact = before.iterator().next();
        app.contact().delete(modifiedContact);
        Set<ContactDate> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(modifiedContact);
        Assert.assertEquals(before,after);
    }

}
