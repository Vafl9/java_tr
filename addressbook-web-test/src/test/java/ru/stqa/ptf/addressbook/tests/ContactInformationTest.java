package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactInformationTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().createNewContact(new ContactDate().withName("Andrew").withLastName("Dzhodzhua").withEmail("Head@mail.ru").withGroup("Test1"), true);
        }
    }

    @Test
    public void testContactInformation()
    {
        app.goTo().contactPage();
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInformation = app.contact().infoFromInformationForm(contact);

        assertThat(cleaned(contact.getAllContactInformation()), equalTo(mergeInformation(contactInformation)));

    }

    private String mergeInformation(ContactDate contact) {
        return Stream.of(contact.getAllContactInformation()).filter((s) -> !s.equals(""))
                .map(ContactInformationTest::cleaned)
                .collect(Collectors.joining("\n"));

    }

    private static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "").replaceAll("H:","").replaceAll("W:","").replaceAll("M:","");
    }

}
