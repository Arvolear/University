package university.boot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import university.boot.models.User;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LogoutController extends AppAbstractController {
    @GetMapping("api/logout")
    public void logout(
            @CookieValue(name = "authtoken") String token,
            HttpServletResponse response
    ) {
        try {
            User currentUser = getUser(token);
            userManager.logout(currentUser.authToken);

            response.setStatus(HttpStatus.OK.value());
            response.sendRedirect(context.getContextPath() + "/login");
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            ex.printStackTrace();
        }
    }
}
