package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;

public class GroupCreationTest extends TestBase {

    @Test
    public void groupCreationTest(){
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupsHelper().getGroupCount();
        app.getGroupsHelper().createGroup(new GroupDate("Test1", "Test1", "Test1"));
        int after = app.getGroupsHelper().getGroupCount();
        Assert.assertEquals(after,before+1);
    }

}
