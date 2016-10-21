package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


    @Test
    public void ContactCreationTest() {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/stru.png");
        ContactDate contact = new ContactDate().withName("Andrew").withLastName( "Dzhodzhua").withEmail("Head@mail.ru").withPhoto(photo);
        app.contact().createNewContact(contact, true);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }
}
