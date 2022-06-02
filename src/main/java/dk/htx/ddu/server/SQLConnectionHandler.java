package dk.htx.ddu.server;

import com.mysql.cj.conf.ConnectionUrlParser;

import javax.swing.JButton;
import java.awt.geom.Path2D;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class SQLConnectionHandler {

    Connection conn;
    Statement stat;

    public SQLConnectionHandler(String ip, int port, String username, String password) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/reviews", username, password);
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void startSQLConn(String uid, int rating, String place, String timeMillis) {
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM reviews WHERE uid='" + uid + "'");
            if (rs.next()) {
                stat.executeUpdate("DELETE FROM reviews WHERE uid='" + uid + "'");
            }

            stat.executeUpdate("INSERT INTO reviews (uid, rating, timestamp, place) VALUES ('" + uid + "', " + rating + ", '" + timeMillis + "', '" + place + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double startSQLConnUpdate(String place) {
        ArrayList<Integer> ratings = new ArrayList<>();
        double averageRating = 0.00;
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM reviews WHERE place='" + place + "'");
            while (rs.next()) {
                ratings.add(rs.getInt("rating"));
            }

            for (Integer i : ratings) {
                averageRating = averageRating + i;
            }
            averageRating = averageRating / ratings.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageRating;
    }

    public ArrayList<ArrayList> startSQLConnOverview() {
        ArrayList<String> places = new ArrayList<>();
        ArrayList<Integer> ratings = new ArrayList<>();
        ArrayList<Double> averageRatings = new ArrayList<>();
        double averageRating = 0.00;
        try {
            ResultSet rs = stat.executeQuery("SELECT place FROM reviews");
            while (rs.next()) {
                if (!places.contains(rs.getString("place")))
                    places.add(rs.getString("place"));
            }
            for (String s : places) {
                ResultSet rs2 = stat.executeQuery("SELECT * FROM reviews WHERE place='" + s + "'");
                while (rs2.next()) {
                    ratings.add(rs2.getInt("rating"));
                }
                for (Integer i : ratings) {
                    averageRating = averageRating + i;
                }
                averageRating = averageRating / ratings.size();
                averageRatings.add(averageRating);
                ratings.clear();
                averageRating = 0.00;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList> arrayList = new ArrayList<>();
        arrayList.add(places);
        arrayList.add(averageRatings);
        return arrayList;
    }
}
