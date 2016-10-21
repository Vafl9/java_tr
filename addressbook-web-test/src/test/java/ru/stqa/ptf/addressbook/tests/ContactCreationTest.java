package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {


    @Test
    public void ContactCreationTest() {
        app.Contact().goToContactPage();
        List<ContactDate> before = app.Contact().getContactList();
        ContactDate contact = new ContactDate("Andrew", "Dzhodzhua", "Head@mail.ru", "Test1");
        app.Contact().createNewContact(contact, true);
        List<ContactDate> after = app.Contact().getContactList();
        Assert.assertEquals(after.size(),before.size()+1);



        contact.setId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
        before.add(contact);

        Comparator<? super ContactDate> byId = (c1,c2) -> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }


}
