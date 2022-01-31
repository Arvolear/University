package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHelper implements AutoCloseable {
    public Connection connection;
    public Statement statement;

    private void initMysql(Environment environment) throws SQLException {
        String connectionString = environment.getJDBC_URL();
        String dbUser = environment.getDB_USER();
        String dbPassword = environment.getDB_PASSWORD();

        connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        statement = connection.createStatement();
    }

    private DataBaseHelper(Environment environment) throws SQLException {
        initMysql(environment);
    }

    public static DataBaseHelper getInstance(Environment environment) throws SQLException {
        return new DataBaseHelper(environment);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
