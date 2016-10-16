package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void ContactDeletionTest(){
        app.getGroupsHelper().selectGroup();
        app.getContactHelper().deleteUser();
    }

}
