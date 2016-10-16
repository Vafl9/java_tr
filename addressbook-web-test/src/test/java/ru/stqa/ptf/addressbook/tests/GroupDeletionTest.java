package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testDropDeletionTest() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupsHelper().selectGroup();
        app.getGroupsHelper().deleteCreationGroup();
        app.getGroupsHelper().returnToGroupPage();
    }
}
