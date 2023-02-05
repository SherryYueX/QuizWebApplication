package quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.domain.User;

import java.util.Optional;

@Service
public class LoginService {
    private final UserService userService;

    @Autowired
    public LoginService(UserService userService) {this.userService = userService; }

    public Optional<User> validateLogin(String username) {
        return userService.validateLogin(username);
    }

    public User getUserById(String userName) {
        return userService.getUserById(userName);
    }
}
