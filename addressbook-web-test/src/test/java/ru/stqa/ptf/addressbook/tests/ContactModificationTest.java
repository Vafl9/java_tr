package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {


    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contact().createNewContact(new ContactData().withName("Andrew").withLastName("Dzhodzhua").withEmail("Head@mail.ru"), true);
        }
    }


    @Test
    public void testContactModificationTest() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withName("Dzhodzhua").withLastName("Dzhodzhua").withEmail("Head@mail.ru").withId(modifiedContact.getId());
        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

        verifyContactListInUi();


    }


}
