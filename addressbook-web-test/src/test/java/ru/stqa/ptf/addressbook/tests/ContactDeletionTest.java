package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void ContactDeletionTest(){
        if(!app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().createNewContact(new ContactData("Andrew", "Dzhodzhua", "Head@mail.ru","Test1"),true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteUser();
    }

}
