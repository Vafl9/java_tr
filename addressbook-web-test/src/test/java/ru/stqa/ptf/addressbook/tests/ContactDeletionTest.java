package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void ContactDeletionTest(){
        if(!app.Contact().isThereAContact())
        {
            app.Contact().createNewContact(new ContactDate("Andrew", "Dzhodzhua", "Head@mail.ru","Test1"),true);
        }
        List<ContactDate> before = app.Contact().getContactList();
        app.Contact().selectContact(before.size()-1);
        app.Contact().deleteUser();
        List<ContactDate> after = app.Contact().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(before,after);
    }

}
