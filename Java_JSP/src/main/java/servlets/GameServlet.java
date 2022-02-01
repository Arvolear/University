package servlets;

import common.GameManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameServlet extends AppAbstractServlet {
    protected GameManager gameManager = new GameManager();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println(gameManager.getScore(getUser(request)));
            response.setStatus(OK);
        } catch (Exception ex) {
            response.setStatus(BAD_REQUEST);
            ex.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.getWriter().println(gameManager.updateScore(getUser(request)));
            response.setStatus(OK);
        } catch (Exception ex) {
            response.sendRedirect(request.getContextPath() + "/login");
            response.setStatus(BAD_REQUEST);
            ex.printStackTrace();
        }
    }
}
