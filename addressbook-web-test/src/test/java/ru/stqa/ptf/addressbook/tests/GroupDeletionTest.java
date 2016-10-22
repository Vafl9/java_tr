package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withHeader("Test1").withHeader("Test1").withFooter("Test1"));
        }
    }

    @Test
    public void testGroupDeletionTest() {

        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().getGroupCount(), equalTo(before.size() - 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(deletedGroup)));

        verifyGroupListInUi();

    }






}
