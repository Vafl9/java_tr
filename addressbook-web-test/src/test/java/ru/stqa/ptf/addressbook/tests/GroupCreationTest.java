package ru.stqa.ptf.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupDate;
import ru.stqa.ptf.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroupsJSON() throws IOException {

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupDate> groups = gson.fromJson(json, new TypeToken<List<GroupDate>>() {
            }.getType());

            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    @DataProvider
    public Iterator<Object[]> validGroupsXML() throws IOException {

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xsteam = new XStream();
            xsteam.processAnnotations(GroupDate.class);
            List<GroupDate> groups = (List<GroupDate>) xsteam.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsXML")
    public void groupCreationTest(GroupDate group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
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
