package com.company;

import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SQLConnectionHandler {

    public void startSQLConn(String uid) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/reviews", "root", "root");

            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT 1 FROM reviews WHERE uid='" + uid + "'");
            if (rs.next()) {
                System.out.println("Card already scanned");
            } else stat.executeUpdate("INSERT INTO reviews (uid, rating) VALUES ('" + uid + "', 5)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
