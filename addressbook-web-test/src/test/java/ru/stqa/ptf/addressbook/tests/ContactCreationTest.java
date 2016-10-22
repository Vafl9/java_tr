package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDate;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContacts(){
        File photo = new File("src/test/resources/stru.png");
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new ContactDate().withName("Andrew1").withLastName( "Dzhodzhua1").withEmail("Head1@mail.ru").withPhoto(photo)});
        list.add(new Object[] {new ContactDate().withName("Andrew2").withLastName( "Dzhodzhua2").withEmail("Head2@mail.ru").withPhoto(photo)});
        list.add(new Object[] {new ContactDate().withName("Andrew3").withLastName( "Dzhodzhua3").withEmail("Head3@mail.ru").withPhoto(photo)});
        return list.iterator();
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
