package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoGroupPage();

        if (app.group().list().size() == 0) {
            app.group().create(new GroupDate().withName("Test1").withHeader("Test").withFooter("Test"));
        }
    }


    @Test
    public void TestGroupModification() {
        List<GroupDate> before = app.group().list();
        int index = before.size() - 1;
        GroupDate group = new GroupDate()
                .withName("Test1").withHeader("Test").withFooter("Test").withId(before.get(index).getId());
        app.group().modify(index, group);
        List<GroupDate> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());


        before.remove(index);
        before.add(group);

        Comparator<? super GroupDate> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);


    }


}
