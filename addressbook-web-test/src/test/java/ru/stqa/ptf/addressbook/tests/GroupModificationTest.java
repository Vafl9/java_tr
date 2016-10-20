package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase {

    @Test
    public void TestGroupModification(){
        app.getNavigationHelper().gotoGroupPage();

        if(! app.getGroupsHelper().isThereAGroup())
        {
            app.getGroupsHelper().createGroup(new GroupDate("Test1", "Test1", "Test1"));
        }
        List<GroupDate> before = app.getGroupsHelper().getGroupList();
        app.getGroupsHelper().selectGroup(before.size() - 1);
        app.getGroupsHelper().initGroupModification();
        GroupDate group = new GroupDate("New_Test1", "Test1", "Test1", before.get(before.size() - 1).getId());
        app.getGroupsHelper().fillGroupForm(group);
        app.getGroupsHelper().submitGroupModification();
        app.getGroupsHelper().returnToGroupPage();
        List<GroupDate> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() - 1);
        before.add(group);

        Comparator<? super GroupDate> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before,after);


    }
}
