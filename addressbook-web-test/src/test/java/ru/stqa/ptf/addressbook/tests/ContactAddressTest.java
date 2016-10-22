package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contact().createNewContact(new ContactData().withName("Andrew").withLastName("Dzhodzhua").withEmail("Head@mail.ru").withGroup("Test1"), true);
        }
    }

    @Test
    public void testContactAddress()
    {
        app.goTo().contactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(cleaned(contact.getAddress()), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

    private static String cleaned(String phone) {
        return phone.replaceAll("\\s", "");
    }
}
