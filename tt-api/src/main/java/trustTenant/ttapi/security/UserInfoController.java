package trustTenant.ttapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserInfoController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users/{id}/email")
    public String getEmailById(
            @PathVariable long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(!user.getRole().equals("LORD")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return user.getEmail();
    }
}
