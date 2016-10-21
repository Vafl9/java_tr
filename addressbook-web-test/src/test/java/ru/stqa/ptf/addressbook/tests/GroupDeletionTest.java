package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.util.Set;

public class GroupDeletionTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().all().size() == 0) {
            app.group().create(new GroupDate().withHeader("Test1").withHeader("Test1").withFooter("Test1"));
        }
    }

    @Test
    public void testGroupDeletionTest() {

        Set<GroupDate> before = app.group().all();
        GroupDate deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupDate> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);


    }


}
