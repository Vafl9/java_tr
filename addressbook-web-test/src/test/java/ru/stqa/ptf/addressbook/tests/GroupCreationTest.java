package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTest extends TestBase {

    @Test
    public void groupCreationTest() {
        app.goTo().gotoGroupPage();
        Set<GroupDate> before = app.group().all();
        GroupDate group = new GroupDate().withName("Test1").withHeader("Test").withFooter("Test");
        app.group().create(group);
        Set<GroupDate> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(group);

        Assert.assertEquals(before, after);
    }

}
