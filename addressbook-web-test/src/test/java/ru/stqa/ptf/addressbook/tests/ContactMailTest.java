package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactMailTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contact().createNewContact(new ContactData().withName("Andrew").withLastName("Dzhodzhua").withEmail("Head@mail.ru"), true);
        }
    }

    @Test
    public void testContactPhone() {
        app.goTo().contactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllMail(), CoreMatchers.equalTo(mergeEmail(contactInfoFromEditForm)));
    }

    private String mergeEmail(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getSecondEmail()).filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));

    }



}
