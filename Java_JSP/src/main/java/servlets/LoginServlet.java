package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends AppAbstractServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            response.addCookie(userManager.login(userManager.authenticate(email, password)));
            response.sendRedirect(request.getContextPath() + "/game");
            response.setStatus(OK);
        } catch (Exception ex) {
            response.sendRedirect(request.getContextPath() + "/login");
            response.setStatus(BAD_REQUEST);
            ex.printStackTrace();
        }
    }
}
