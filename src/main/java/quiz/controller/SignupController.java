package quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import quiz.domain.User;
import quiz.service.SignupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService=signupService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignup() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String postSignup(@RequestParam String userName,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String emailAddr,
                             @RequestParam String physicalAddr,
                             @RequestParam String phoneNumber,
                             @RequestParam String password,
                             @RequestParam String status,
                             Model model) {
        Optional<User> possibleUser = signupService.validateLogin(userName);
        if(possibleUser.isPresent()){
            model.addAttribute("existingUserName", userName);
            return "failedSignup";
        }
        else{
            signupService.createNewUser(new User(userName, firstName, lastName,
                                        emailAddr, physicalAddr, phoneNumber, password, status));
            return "successfulSignup";
        }
    }
}
