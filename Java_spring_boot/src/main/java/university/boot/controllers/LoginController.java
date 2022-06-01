package university.boot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController extends AppAbstractController {
    @GetMapping("api/login")
    public void checkLogin(
            @CookieValue(name = "authtoken", required = false) String token,
            HttpServletResponse response
    ) {
        try {
            getUser(token);
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            ex.printStackTrace();
        }
    }

    @PostMapping("api/login")
    public void login(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            HttpServletResponse response
    ) throws IOException {
        try {
            response.addCookie(userManager.login(userManager.authenticate(email, password)));
            response.sendRedirect(context.getContextPath() + "/game");
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex) {
            response.sendRedirect(context.getContextPath() + "/login");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            ex.printStackTrace();
        }
    }
}
