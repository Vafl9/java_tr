package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class DeleteGroupFromContactTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contact().createNewContact(new ContactData().withName("Andrew").withLastName("Dzhodzhua").withEmail("Head@mail.ru"), true);
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("Test1").withHeader("Test").withFooter("Test"));
        }
        app.goTo().contactPage();
    }

    @Test
    public void testDeleteGroupFromContact()
    {
        Groups groups = app.db().groups();
        Contacts contactWithGroup = app.db().contacts();
        app.contact().deleteGroupFromContact(contactWithGroup.iterator().next(), groups.iterator().next());
        assertThat(app.contact().getContactCount(), equalTo(contactWithGroup.size()));
        verifyContactListInUi();
    }
}
