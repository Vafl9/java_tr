package ru.stqa.ptf.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    public Iterator<Object[]> validContactsJSON() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactDate> contacts = gson.fromJson(json, new TypeToken<List<ContactDate>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    @DataProvider
    public Iterator<Object[]> validContactsXML() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xsteam = new XStream();
            xsteam.processAnnotations(ContactDate.class);
            List<ContactDate> contacts = (List<ContactDate>) xsteam.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsXML")
    public void ContactCreationTest(ContactDate contact) {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        app.contact().createNewContact(contact, true);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
