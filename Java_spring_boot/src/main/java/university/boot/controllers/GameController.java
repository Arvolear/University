package university.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import university.boot.managers.GameManager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class GameController extends AppAbstractController {
    @Autowired
    protected GameManager gameManager;

    @GetMapping("api/game")
    public void getScore(
            @CookieValue(name = "authtoken") String token,
            HttpServletResponse response
    ) {
        try {
            response.getWriter().println(gameManager.getScore(getUser(token)));
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            ex.printStackTrace();
        }
    }

    @PostMapping("api/game")
    public void updateScore(
            @CookieValue(name = "authtoken") String token,
            HttpServletResponse response
    ) throws IOException {
        try {
            response.getWriter().println(gameManager.updateScore(getUser(token)));
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex) {
            response.sendRedirect(context.getContextPath() + "/login");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            ex.printStackTrace();
        }
    }
}
