package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void groupCreationTest() {
        app.goTo().gotoGroupPage();
        List<GroupDate> before = app.group().list();
        GroupDate group = new GroupDate().withName("Test1").withHeader("Test").withFooter("Test");
        app.group().create(group);
        List<GroupDate> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);

        Comparator<? super GroupDate> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
