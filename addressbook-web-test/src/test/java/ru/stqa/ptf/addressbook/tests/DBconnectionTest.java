package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.sql.*;

public class DBconnectionTest {

    @Test
    public void TestDBconnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=&serverTimezone=UTC");

            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM group_list ");

            Groups group =  new Groups();
            while (resultSet.next())
            {
                group.add(new GroupData().withName(resultSet.getString("group_name")).withId(resultSet.getInt("group_id")));
            }
            resultSet.close();
            st.close();
            conn.close();
            System.out.println(group);
            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
