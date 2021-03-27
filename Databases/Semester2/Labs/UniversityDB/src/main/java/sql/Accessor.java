package sql;

import java.sql.*;

public class Accessor implements AutoCloseable {
    private static Accessor singletonAccessor = null;

    private static final String LOGIN = "uni";
    private static final String PASSWORD = "uni";

    private static final String DB = "university_hotel";

    private final Connection con;
    private final Statement stat;

    private Accessor() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection("jdbc:mysql://localhost/" + DB + "?" +
                "useUnicode=true" +
                "&user=" + LOGIN +
                "&password=" + PASSWORD);

        stat = con.createStatement();
    }

    public static Accessor getInstance() throws Exception {
        if (singletonAccessor == null) {
            singletonAccessor = new Accessor();
        }

        return singletonAccessor;
    }

    public ResultSet executeQuery(String query) {
        try {
            return stat.executeQuery(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}