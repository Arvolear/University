package university.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import university.boot.managers.UserManager;
import university.boot.models.User;

import javax.servlet.ServletContext;
import java.sql.SQLException;

public abstract class AppAbstractController {
    @Autowired
    protected UserManager userManager;
    @Autowired
    protected ServletContext context;

    public User getUser(String token) throws SQLException {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("No authentication token present in the request");
        }

        return userManager.getUserFromSession(userManager.getSessionFromToken(token));
    }
}
