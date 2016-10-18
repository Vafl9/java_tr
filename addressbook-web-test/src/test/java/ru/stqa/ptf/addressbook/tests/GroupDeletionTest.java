package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testDropDeletionTest() {
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupsHelper().isThereAGroup())
        {
            app.getGroupsHelper().createGroup(new GroupDate("Test1", "Test1", "Test1"));
        }

        app.getGroupsHelper().selectGroup();
        app.getGroupsHelper().deleteCreationGroup();
        app.getGroupsHelper().returnToGroupPage();
    }
}
