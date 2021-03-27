import lab1.Laboratory;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void first(Laboratory lab) throws SQLException {
        System.out.println("Enter capacity:");

        int capacity;
        Scanner scanner = new Scanner(System.in);

        capacity = scanner.nextInt();

        lab.printRoomsWithPrice(capacity);
    }

    public static void second(Laboratory lab) throws SQLException {
        System.out.println("Enter comfort and date:");

        String comfort, date;
        Scanner scanner = new Scanner(System.in);

        comfort = scanner.nextLine();
        date = scanner.nextLine();

        lab.printFreeRooms(comfort, date);
    }

    public static void third(Laboratory lab) throws SQLException {
        System.out.println("Enter room number and number of days");

        int roomNum, days;
        Scanner scanner = new Scanner(System.in);

        roomNum = scanner.nextInt();
        days = scanner.nextInt();

        lab.printPrice(roomNum, days);
    }

    public static void fourth(Laboratory lab) throws SQLException {
        lab.printComfort();
    }

    public static void fifth(Laboratory lab) throws SQLException {
        lab.printFreeToday();
    }

    public static void main(String[] args) {
        try (Laboratory lab = new Laboratory()) {
            first(lab);
            second(lab);
            third(lab);
            fourth(lab);
            fifth(lab);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}