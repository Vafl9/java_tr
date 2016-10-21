package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.util.Set;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().all().size() == 0) {
            app.group().create(new GroupDate().withName("Test1").withHeader("Test").withFooter("Test"));
        }
    }


    @Test
    public void TestGroupModification() {
        Set<GroupDate> before = app.group().all();
        GroupDate modifiedGroup = before.iterator().next();
        GroupDate group = new GroupDate()
                .withName("Test1").withHeader("Test").withFooter("Test").withId(modifiedGroup.getId());
        app.group().modify(group);
        Set<GroupDate> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());


        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals(before, after);


    }


}
