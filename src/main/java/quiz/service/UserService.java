package quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.dao.UserDAO;
import quiz.domain.User;

import java.util.Optional;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserById(String userName) {
        return userDAO.getUserByUserName(userName);
    }

    public void createNewUser(User user) {
        userDAO.createNewUser(user);
    }

    public Optional<User> validateLogin(String username) {
        return userDAO.getAllUsers().stream()
                .filter(a -> a.getUserName().equals(username))
                .findAny();
    }
}
