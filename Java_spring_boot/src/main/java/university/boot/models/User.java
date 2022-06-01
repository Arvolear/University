package university.boot.models;

import java.util.Objects;

public class User {
    public int id;
    public String authToken;
    public String email;
    public String nickname;

    public User(String email, String nickname, int id) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public User(String email, String nickname, int id, String authToken) {
        this.email = email;
        this.nickname = nickname;
        this.authToken = authToken;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
