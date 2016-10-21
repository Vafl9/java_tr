package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.util.List;

public class GroupDeletionTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoGroupPage();

        if (app.group().list().size() == 0) {
            app.group().create(new GroupDate().withHeader("Test1").withHeader("Test1").withFooter("Test1"));
        }
    }

    @Test
    public void testGroupDeletionTest() {

        List<GroupDate> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupDate> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);


    }


}
