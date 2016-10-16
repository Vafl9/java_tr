package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.UserData;

public class UserCreationTest extends TestBase {

    

    @Test
    public void UserCreationTest(){
        app.getContactHelper().createUser(new UserData("Andrew", "Dzhodzhua", "Head@mail.ru"));
    }


}
