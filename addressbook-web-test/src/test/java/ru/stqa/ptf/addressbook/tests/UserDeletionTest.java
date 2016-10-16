package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTest extends TestBase {

    @Test
    public void UserDeletionTest(){
        app.getGroupsHelper().selectGroup();
        app.getContactHelper().deleteUser();
    }

}
