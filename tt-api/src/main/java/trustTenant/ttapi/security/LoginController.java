package trustTenant.ttapi.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST, value = "/session")
    public void authUser(
            @RequestBody UserEntity user,
            HttpServletResponse response) {
        String token = String.valueOf(loginService.getSessionToken(user));

        response.addCookie(new Cookie("trustToken", token));
        response.setStatus(201);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/session")
    public void logoutUser(
            @RequestBody String token) {
        loginService.endSession(Long.parseLong(token));
    }

    @RequestMapping("/validate")
    public UserEntity validateUser(
            @RequestBody long token) {
        UserEntity user = loginService.getSessionUser(token);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String registerUser(@RequestBody UserEntity user, HttpServletResponse response) {
        user.setRole("USER");
        try {
            long userId = loginService.registerUser(user);
            
            String token = String.valueOf(userId);
            
            Cookie cookie = new Cookie("trustToken", token);
            response.addCookie(cookie);
            
            response.setStatus(201);
        } catch (IllegalArgumentException e) {
            response.setStatus(409);
            return e.getMessage();
        }
        return null;
    }
}
