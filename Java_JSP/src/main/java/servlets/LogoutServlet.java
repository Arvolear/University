package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends AppAbstractServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User currentUser = getUser(request);
            this.userManager.logout(currentUser.authToken);

            JsonObject success = new JsonObject();
            success.addProperty("success", true);
            response.getWriter().println(new Gson().toJson(success));
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception ex) {
            response.setStatus(BAD_REQUEST);
            ex.printStackTrace();
        }
    }
}
