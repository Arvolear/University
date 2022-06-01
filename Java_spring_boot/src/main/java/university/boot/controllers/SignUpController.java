package university.boot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import university.boot.models.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SignUpController extends AppAbstractController {
    @PostMapping("api/signup")
    public void signUp(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "nickname") String nick,
            HttpServletResponse response
    ) throws IOException {
        try {
            User user = userManager.signUp(email, password, nick);

            response.addCookie(userManager.login(user));
            response.sendRedirect(context.getContextPath() + "/game");
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex) {
            response.sendRedirect(context.getContextPath() + "/signup");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            ex.printStackTrace();
        }
    }
}
