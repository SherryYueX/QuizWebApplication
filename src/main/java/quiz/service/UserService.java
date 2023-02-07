package quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.dao.FeedbackDAO;
import quiz.dao.MessageDAO;
import quiz.dao.UserDAO;
import quiz.domain.Feedback;
import quiz.domain.Message;
import quiz.domain.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final FeedbackDAO feedbackDAO;
    private final MessageDAO messageDAO;

    @Autowired
    public UserService(UserDAO userDAO, FeedbackDAO feedbackDAO, MessageDAO messageDAO) {
        this.userDAO = userDAO;
        this.feedbackDAO = feedbackDAO;
        this.messageDAO = messageDAO;
    }

    public User getUserById(String userName) {
        return userDAO.getUserByUserName(userName);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public List<Feedback> getAllFeedback(){
        return feedbackDAO.getAllFeedback();
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public void createNewUser(User user) {
        userDAO.createNewUser(user);
    }
    public void createFeedback(Feedback feedback) {
        feedbackDAO.createFeedback(feedback);
    }
    public void createMessage(Message message) {
        messageDAO.createMessage(message);
    }

    public void updateUserByUserName(String userName, String status){
        userDAO.updateUserByUserName(userName, status);
    }
    public Optional<User> validateLogin(String username) {
        return userDAO.getAllUsers().stream()
                .filter(a -> a.getUserName().equals(username))
                .findAny();
    }

    public Optional<Feedback> validateFeedback(int id) {
        return feedbackDAO.getAllFeedback().stream()
                .filter(a -> a.getId()==id)
                .findAny();
    }

    public Optional<Message> validateMessage(int id) {
        return messageDAO.getAllMessages().stream()
                .filter(a -> a.getId()==id)
                .findAny();
    }
}
