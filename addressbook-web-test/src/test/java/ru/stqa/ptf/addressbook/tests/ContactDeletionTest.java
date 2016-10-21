package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;
import ru.stqa.ptf.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.junit.MatcherAssert.*;

public class ContactDeletionTest extends TestBase {


    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().createNewContact(new ContactDate().withName("Andrew").withLastName("Dzhodzhua").withEmail("Head@mail.ru").withGroup("Test1"), true);
        }
    }


    @Test
    public void ContactDeletionTest(){
        Contacts before = app.contact().all();
        ContactDate deletionContact = before.iterator().next();
        app.contact().delete(deletionContact);
        assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withOut(deletionContact)));
    }

}
