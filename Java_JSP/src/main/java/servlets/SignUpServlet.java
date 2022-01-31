package servlets;

import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpServlet extends AppAbstractServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String nick = request.getParameter("nickname");

            User user = userManager.signUp(email, password, nick);

            response.addCookie(userManager.login(user));
            response.sendRedirect(request.getContextPath() + "/game");
            response.setStatus(OK);
        } catch (Exception ex) {
            response.sendRedirect(request.getContextPath() + "/signup");
            response.setStatus(BAD_REQUEST);
            ex.printStackTrace();
        }
    }
}
