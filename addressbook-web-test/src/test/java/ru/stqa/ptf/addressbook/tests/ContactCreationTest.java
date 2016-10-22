package ru.stqa.ptf.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        String xml = "";
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String line = reader.readLine();
        while (line != null)
        {
            xml += line;
            line = reader.readLine();
        }
        XStream xsteam = new XStream();
        xsteam.processAnnotations(ContactDate.class);
        List<ContactDate> contact = (List<ContactDate>) xsteam.fromXML(xml);
        return contact.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContacts")
    public void ContactCreationTest(ContactDate contact) {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        app.contact().createNewContact(contact, true);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }
}
