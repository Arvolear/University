package models;

import java.sql.Timestamp;

public class Session {
    public int userId;
    public String authToken;
    public Timestamp validThrough;

    public Session(int userId, String authToken, Timestamp validThrough) {
        this.userId = userId;
        this.authToken = authToken;
        this.validThrough = validThrough;
    }

    public boolean isValid() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        return currentTimestamp.before(validThrough);
    }
}
