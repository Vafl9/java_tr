package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

public class GroupCreationTest extends TestBase {

    @Test
    public void groupCreationTest() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupsHelper().createGroup(new GroupDate("Test1", "Test1", "Test1"));
    }

}
