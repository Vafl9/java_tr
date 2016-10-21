package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactMailTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().createNewContact(new ContactDate().withName("Andrew").withLastName("Dzhodzhua").withEmail("Head@mail.ru").withGroup("Test1"), true);
        }
    }

    @Test
    public void testContactPhone() {
        app.goTo().contactPage();
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllMail(), CoreMatchers.equalTo(mergeEmail(contactInfoFromEditForm)));
    }

    private String mergeEmail(ContactDate contact) {
        return Stream.of(contact.getEmail(), contact.getSecondEmail()).filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));

    }



}
