package common;

import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameManager {
    protected Environment environment;

    public GameManager() {
        this.environment = new Environment();
    }

    public int getScore(User user) throws SQLException {
        try (DataBaseHelper dbHelper = DataBaseHelper.getInstance(environment)) {
            PreparedStatement ps = dbHelper.connection.prepareStatement(SqlQueryStorage.GET_SCORE_BY_EMAIL);
            ps.setString(1, user.email);
            ResultSet resultSet = ps.executeQuery();

            resultSet.next();

            return resultSet.getInt(SqlQueryStorage.SCORE);
        }
    }

    public int updateScore(User user) throws SQLException {
        try (DataBaseHelper dbHelper = DataBaseHelper.getInstance(environment)) {
            PreparedStatement ps = dbHelper.connection.prepareStatement(SqlQueryStorage.UPDATE_SCORE);
            int newScore = getScore(user) + 1;

            ps.setInt(1, newScore);
            ps.setString(2, user.email);
            ps.executeUpdate();

            return newScore;
        }
    }
}
