package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {


    @Test
    public void ContactCreationTest() {
        app.goTo().contactPage();
        Set<ContactDate> before = app.contact().all();
        ContactDate contact = new ContactDate().withName("Andrew").withLastName( "Dzhodzhua").withMail("Head@mail.ru").withGroup("Test1");
        app.contact().createNewContact(contact, true);
        Set<ContactDate> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size()+1);


        contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(contact);

        Assert.assertEquals(before,after);
    }


}
