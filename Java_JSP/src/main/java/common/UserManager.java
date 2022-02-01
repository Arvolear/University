package common;

import models.Session;
import models.User;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.http.Cookie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class UserManager {
    public int THIRTY_DAYS_SECONDS = 30 * 24 * 60 * 60;

    protected Environment environment;
    protected PasswordHasher passwordHasher;

    public UserManager() {
        this.environment = new Environment();
        this.passwordHasher = new PasswordHasher(environment);
    }

    public User signUp(String email, String password, String nick) throws SQLException {
        try (DataBaseHelper dbHelper = DataBaseHelper.getInstance(environment)) {
            if (email == null || password == null || nick == null ||
                    email.length() == 0 || password.length() == 0 || nick.length() == 0) {
                throw new RuntimeException("Email, password and nick must not be empty");
            }

            if (!EmailValidator.getInstance().isValid(email)) {
                throw new RuntimeException("Invalid email");
            }

            PreparedStatement ps = dbHelper.connection.prepareStatement(SqlQueryStorage.CHECK_EMAIL_OR_NICK_TAKEN);
            ps.setString(1, email);
            ps.setString(2, nick);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                throw new RuntimeException("Email or nickname is already taken");
            }

            ps = dbHelper.connection.prepareStatement(SqlQueryStorage.CREATE_USER);
            ps.setString(1, nick);
            ps.setString(2, email);
            ps.setString(3, passwordHasher.hash(password));
            ps.executeUpdate();

            ps = dbHelper.connection.prepareStatement(SqlQueryStorage.CREATE_SCORE);
            ps.setString(1, email);
            ps.setInt(2, 0);
            ps.executeUpdate();

            ps = dbHelper.connection.prepareStatement(SqlQueryStorage.GET_USER_BY_EMAIL);
            ps.setString(1, email);
            resultSet = ps.executeQuery();

            resultSet.next();

            User user = new User(email, nick, resultSet.getInt(SqlQueryStorage.USER_ID));
            login(user);

            return user;
        }
    }

    public User authenticate(String email, String password) throws SQLException {
        try (DataBaseHelper dbHelper = DataBaseHelper.getInstance(environment)) {
            if (email == null || password == null) {
                throw new RuntimeException("Password or email is null");
            }

            PreparedStatement ps = dbHelper.connection.prepareStatement(SqlQueryStorage.USER_AUTH);
            ps.setString(1, email);
            ps.setString(2, passwordHasher.hash(password));
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return new User(email, resultSet.getString(SqlQueryStorage.USER_NICKNAME), resultSet.getInt(SqlQueryStorage.USER_ID));
            } else {
                throw new RuntimeException("Invalid username/password");
            }
        }
    }

    public Cookie login(User user) throws SQLException {
        try (DataBaseHelper dbHelper = DataBaseHelper.getInstance(environment)) {
            logout(user.authToken);
            user.authToken = UUID.randomUUID().toString();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis() + THIRTY_DAYS_SECONDS * 1000L);

            PreparedStatement ps = dbHelper.connection.prepareStatement(SqlQueryStorage.LOGIN_USER);
            ps.setInt(1, user.id);
            ps.setString(2, user.authToken);
            ps.setTimestamp(3, timestamp);
            ps.executeUpdate();

            Cookie authTokenCookie = new Cookie("authtoken", user.authToken);
            authTokenCookie.setMaxAge(THIRTY_DAYS_SECONDS);
            authTokenCookie.setPath("/");

            return authTokenCookie;
        }
    }

    public void logout(String authToken) throws SQLException {
        if (authToken == null) {
            return;
        }

        try (DataBaseHelper dbHelper = DataBaseHelper.getInstance(environment)) {
            PreparedStatement ps = dbHelper.connection.prepareStatement(SqlQueryStorage.LOGOUT_USER);
            ps.setString(1, authToken);
            ps.executeUpdate();
        }
    }

    public Session getSessionFromToken(String authToken) throws SQLException {
        if (authToken == null) {
            throw new RuntimeException("Session is null");
        }

        try (DataBaseHelper dbHelper = DataBaseHelper.getInstance(environment)) {
            PreparedStatement ps = dbHelper.connection.prepareStatement(SqlQueryStorage.GET_AUTH_TOKEN_DATA);
            ps.setString(1, authToken);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return new Session(
                        resultSet.getInt(SqlQueryStorage.SESSION_USER),
                        authToken,
                        resultSet.getTimestamp(SqlQueryStorage.SESSION_VALID_THROUGH)
                );
            }

            throw new RuntimeException("No session associated with the authentication token");
        }
    }

    public User getUserFromSession(Session session) throws SQLException {
        if (session.isValid()) {
            try (DataBaseHelper dbHelper = DataBaseHelper.getInstance(environment)) {
                PreparedStatement ps = dbHelper.connection.prepareStatement(SqlQueryStorage.GET_USER_BY_ID);
                ps.setInt(1, session.userId);
                ResultSet resultSet = ps.executeQuery();
                
                if (resultSet.next()) {
                    return new User(
                            resultSet.getString(SqlQueryStorage.USER_EMAIL),
                            resultSet.getString(SqlQueryStorage.USER_NICKNAME),
                            session.userId,
                            session.authToken
                    );
                } else {
                    throw new RuntimeException("Not user associated with the session");
                }
            }
        } else {
            throw new RuntimeException("Invalid session");
        }
    }
}
