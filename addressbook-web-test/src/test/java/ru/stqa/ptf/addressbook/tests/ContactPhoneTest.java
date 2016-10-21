package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

public class ContactPhoneTest extends TestBase {


    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().createNewContact(new ContactDate().withName("Andrew").withLastName("Dzhodzhua").withMail("Head@mail.ru").withGroup("Test1"), true);
        }
    }

    @Test
    public void testContactPhone()
    {
        app.goTo().contactPage();
        ContactDate contact = app.contact().all().iterator().next();
        //ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
