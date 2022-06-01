package university.boot.common;

public class SqlQueryStorage {
    public static final String USER_NICKNAME = "nick";
    public static final String USER_EMAIL = "email";
    public static final String USER_ID = "id";
    public static final String USER_PASSWORD = "password";

    public static final String SESSION_AUTH_TOKEN = "auth_token";
    public static final String SESSION_USER = "ref_user";
    public static final String SESSION_VALID_THROUGH = "valid_through";

    public static final String SCORE = "score";

    public static final String USER_AUTH = String.format("SELECT %s, %s from User WHERE %s = ? and %s = ?",
            USER_ID, USER_NICKNAME, USER_EMAIL, USER_PASSWORD);

    public static final String GET_USER_BY_ID = String.format("SELECT %s, %s from User WHERE %s = ?",
            USER_NICKNAME, USER_EMAIL, USER_ID);
    public static final String GET_USER_BY_EMAIL = String.format("SELECT %s, %s from User WHERE %s = ?",
            USER_NICKNAME, USER_ID, USER_EMAIL);

    public static final String CHECK_EMAIL_OR_NICK_TAKEN = String.format("SELECT %s from User WHERE %s = ? or %s = ?",
            USER_ID, USER_EMAIL, USER_NICKNAME);

    public static final String CREATE_USER = String.format("INSERT INTO User(%s, %s, %s) values(?, ?, ?)",
            USER_NICKNAME, USER_EMAIL, USER_PASSWORD);
    public static final String LOGIN_USER = String.format("INSERT INTO Session(%s, %s, %s) values(?, ?, ?)",
            SESSION_USER, SESSION_AUTH_TOKEN, SESSION_VALID_THROUGH);
    public static final String LOGOUT_USER = String.format("DELETE FROM Session WHERE %s = ?",
            SESSION_AUTH_TOKEN);

    public static final String GET_AUTH_TOKEN_DATA = String.format("SELECT %s, %s from Session WHERE %s = ?",
            SESSION_USER, SESSION_VALID_THROUGH, SESSION_AUTH_TOKEN);

    public static final String CREATE_SCORE = String.format("INSERT INTO Score(%s, %s) values(?, ?)", USER_EMAIL, SCORE);
    public static final String UPDATE_SCORE = String.format("UPDATE Score SET %s = ? WHERE %s = ?", SCORE, USER_EMAIL);
    public static final String GET_SCORE_BY_EMAIL = String.format("SELECT %s FROM Score WHERE %s = ?", SCORE, USER_EMAIL);
}
