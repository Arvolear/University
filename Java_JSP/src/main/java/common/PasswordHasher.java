package common;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHasher {
    private Environment environment;

    public PasswordHasher(Environment environment) {
        this.environment = environment;
    }

    public String hash(String password) {
        return DigestUtils.sha256Hex(password + environment.getSALT());
    }
}
