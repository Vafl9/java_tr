package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().createNewContact(new ContactDate().withName("Andrew").withLastName("Dzhodzhua").withEmail("Head@mail.ru").withGroup("Test1"), true);
        }
    }

    @Test
    public void testContactAddress()
    {
        app.goTo().contactPage();
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(cleaned(contact.getAddress()), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

    private static String cleaned(String phone) {
        return phone.replaceAll("\\s", "");
    }
}
