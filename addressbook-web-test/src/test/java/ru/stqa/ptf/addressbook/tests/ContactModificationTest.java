package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void ContactModificationTest(){

        if(!app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().createNewContact(new ContactDate("Andrew", "Dzhodzhua", "Head@mail.ru","Test1"),true);
        }
        List<ContactDate> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size()+1);
        System.out.println(before.size());
        ContactDate contact = new ContactDate("Dzhodzhua", "Dzhodzhua", "head@mail.ru",null, before.get(before.size()-1).getId());
        app.getContactHelper().fillFormContact(contact,false);
        app.getContactHelper().submitUpdateContact();
        app.getContactHelper().goToCameraPage();
        List<ContactDate> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);


        Comparator<? super ContactDate> byId = (с1, с2) -> Integer.compare(с1.getId(),с2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before,after);




    }
}
