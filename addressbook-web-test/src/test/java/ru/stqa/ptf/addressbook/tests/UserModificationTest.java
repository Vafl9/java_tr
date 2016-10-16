package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.UserData;

public class UserModificationTest extends TestBase {

    @Test
    public void UserModificationTest(){
        app.getContactHelper().selectUser();
        app.getContactHelper().createUser(new UserData("Andrew", "Dzhodzhua", "Head@mail.ru"));
        app.getContactHelper().submitUpdateUser();

    }
}
