package trustTenant.ttapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    // Pattern from https://www.baeldung.com/java-email-validation-regex
    private final Pattern emailPattern = Pattern.compile(
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*" +
                    "@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    // Maps token to userId
    private HashMap<Long, UserEntity> loggedIn = new HashMap<>();

    // Maps token to local time of creation
    private HashMap<Long, LocalDateTime> sessionStarts = new HashMap<>();

    public long getSessionToken(UserEntity user) {
        UserEntity dbUser = userRepository.findByEmail(user.getEmail());
        if(dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            startSession(dbUser);
            return dbUser.getUserId();
        }
        throw new ErrorResponseException(HttpStatusCode.valueOf(401));
    }

    public void endSession(long token) {
        if(!loggedIn.containsKey(token)) {
            throw new ErrorResponseException(HttpStatusCode.valueOf(204));
        }
        loggedIn.remove(token);
        sessionStarts.remove(token);
    }

    public long registerUser(UserEntity user) {
        validateNewUser(user);
        UserEntity registeredUser = userRepository.save(user);
        startSession(registeredUser);
        return registeredUser.getUserId();
    }


    public UserEntity getSessionUser(long token) {
        if(!sessionStarts.containsKey(token)) {
            return null;
        }

        long timeSpan = MINUTES.between(sessionStarts.get(token), LocalDateTime.now());
        if(timeSpan > 15) {
            endSession(token);
            return null;
        }

        // Refresh
        sessionStarts.replace(token, LocalDateTime.now());
        return loggedIn.get(token);
    }

    private void startSession(UserEntity user) {
        loggedIn.put(user.getUserId(), user);
        sessionStarts.put(user.getUserId(), LocalDateTime.now());
    }

    private void validateNewUser(UserEntity user) {
        String password = user.getPassword();
        if(password == null) {
            throw new IllegalArgumentException("Password field cannot be empty.");
        }
        if(password.length() > 30 || password.length() < 15) {
            throw new IllegalArgumentException("Password must between 15 and 30 characters in length.");
        }

        String email = user.getEmail();
        if(!emailPattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        if(userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException(
                    String.format("An account already exists for email '%s'.", email)
            );
        }
    }
}
