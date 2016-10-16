package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void ContactModificationTest(){
        app.getContactHelper().selectUser();
        app.getContactHelper().createUser(new ContactData("Andrew", "Dzhodzhua", "head@mail.ru", null),false);
        app.getContactHelper().submitUpdateUser();

    }
}
