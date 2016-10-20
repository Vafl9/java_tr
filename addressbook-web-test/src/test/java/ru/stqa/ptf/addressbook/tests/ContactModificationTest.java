package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void ContactModificationTest(){

        if(!app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().createNewContact(new ContactData("Andrew", "Dzhodzhua", "Head@mail.ru","Test1"),true);
        }
        app.getContactHelper().editContact();
        app.getContactHelper().createContact(new ContactData("Andrew", "Dzhodzhua", "head@mail.ru", null),false);
        app.getContactHelper().submitUpdateContact();


    }
}
