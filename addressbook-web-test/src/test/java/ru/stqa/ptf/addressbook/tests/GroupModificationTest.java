package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

public class GroupModificationTest extends TestBase {

    @Test
    public void TestGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupsHelper().isThereAGroup())
        {
            app.getGroupsHelper().createGroup(new GroupDate("Test1", "Test1", "Test1"));
        }
        app.getGroupsHelper().selectGroup();
        app.getGroupsHelper().initGroupModification();
        app.getGroupsHelper().fillGroupForm(new GroupDate("Test1", "Test1", "Test1"));
        app.getGroupsHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();
    }
}
