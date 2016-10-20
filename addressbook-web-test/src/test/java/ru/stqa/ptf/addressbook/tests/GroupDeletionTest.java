package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletionTest() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupsHelper().getGroupCount();
        if(! app.getGroupsHelper().isThereAGroup())
        {
            app.getGroupsHelper().createGroup(new GroupDate("Test1", "Test1", "Test1"));
        }

        app.getGroupsHelper().selectGroup();
        app.getGroupsHelper().deleteCreationGroup();
        app.getGroupsHelper().returnToGroupPage();
        int after = app.getGroupsHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);


    }
}
