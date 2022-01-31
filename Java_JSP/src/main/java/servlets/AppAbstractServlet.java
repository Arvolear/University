package servlets;

import common.UserManager;
import models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public abstract class AppAbstractServlet extends HttpServlet {
    protected final int OK = 200;
    protected final int BAD_REQUEST = 400;

    protected UserManager userManager = new UserManager();

    public User getUser(HttpServletRequest request) throws SQLException {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            throw new RuntimeException("No authentication token present in the request");
        }

        for (Cookie c : cookies) {
            if (c.getName().equals("authtoken")) {
                return userManager.getUserFromSession(userManager.getSessionFromToken(c.getValue()));
            }
        }

        throw new RuntimeException("No authentication token present in the request");
    }
}
