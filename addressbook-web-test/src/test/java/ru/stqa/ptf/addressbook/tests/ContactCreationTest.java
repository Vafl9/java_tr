package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


    @Test
    public void ContactCreationTest() {
        app.getContactHelper().createNewContact(new ContactData("Andrew", "Dzhodzhua", "Head@mail.ru","Test1"),true);
    }


}
