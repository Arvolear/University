package lab1;

import sql.Accessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class Laboratory implements AutoCloseable {
    private Accessor accessor;

    public Laboratory() {
        try {
             accessor = Accessor.getInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void printRoomsWithPrice(int capacity) throws SQLException {
        String query = "SELECT number_room, price FROM Room WHERE capacity = " + capacity;

        ResultSet res = accessor.executeQuery(query);

        while (res.next()) {
            System.out.println(
                    "Room number: " + res.getInt("number_room") + " " +
                    "Price: " + res.getFloat("price"));
        }
    }

    public void printFreeRooms(String comfort, String date) throws SQLException {
        String query =
                "SELECT number_room FROM Renting " +
                "INNER JOIN Room on Renting.ref_room = Room.number_room " +
                "INNER JOIN Comfort on Room.ref_comfort = Comfort.id_comfort " +
                "WHERE (" + date + " < DATE(date_in) " +
                "OR " + date + " > DATE(date_out)) " +
                "AND description = " + "'" + comfort + "'";

        ResultSet res = accessor.executeQuery(query);

        while (res.next()) {
            System.out.println("Room number: " + res.getInt("number_room"));
        }
    }

    public void printPrice(int roomNum, int numberOfDays) throws SQLException {
        String query = "SELECT price FROM Room WHERE number_room = " + roomNum;

        ResultSet res = accessor.executeQuery(query);

        while (res.next()) {
            System.out.println("Total price: " + (res.getFloat("price") * numberOfDays));
        }
    }

    public void printComfort() throws SQLException {
        String query =
                "SELECT description, count(description) AS total FROM Room " +
                "INNER JOIN Comfort on Room.ref_comfort = Comfort.id_comfort " +
                "GROUP BY description";

        ResultSet res = accessor.executeQuery(query);

        TreeMap<String, Integer> comforts = new TreeMap<>();

        while (res.next()) {
            comforts.put(res.getString("description"), res.getInt("total"));
        }

        for (Map.Entry<String, Integer> entry : comforts.entrySet()) {
            System.out.println("Comfort: " + entry.getKey() + " Number: " + entry.getValue());
        }
    }

    public void printFreeToday() throws SQLException {
        String query =
                "SELECT number_room FROM Renting " +
                "INNER JOIN Room on Renting.ref_room = Room.number_room " +
                "WHERE current_date() = DATE(date_out)";

        ResultSet res = accessor.executeQuery(query);

        while (res.next()) {
            System.out.println("Room Number: " + res.getInt("number_room"));
        }
    }

    @Override
    public void close() throws Exception {
        accessor.close();
    }
}
