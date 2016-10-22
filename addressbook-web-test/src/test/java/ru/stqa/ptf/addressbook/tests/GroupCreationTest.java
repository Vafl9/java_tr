package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new GroupDate().withName("name1").withHeader("header1").withHeader("footer1")});
        list.add(new Object[] {new GroupDate().withName("name2").withHeader("header2").withHeader("footer3")});
        list.add(new Object[] {new GroupDate().withName("name3").withHeader("header2").withHeader("footer3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void groupCreationTest(GroupDate group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

    @Test
    public void groupBadCreationTest() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupDate group = new GroupDate().withName("Test1'").withHeader("Test").withFooter("Test");
        app.group().create(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }

}
