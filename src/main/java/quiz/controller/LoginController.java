package quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quiz.domain.User;
import quiz.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRoot(HttpSession session){
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

//         redirect to /quiz if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        model.addAttribute("login", 0);

        return "login";
    }

    // validate that we are always getting a new session after login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@RequestParam String userName,
                            @RequestParam String password,
                            HttpServletRequest request, Model model) {

        Optional<User> possibleUser = loginService.validateLogin(userName);

        if(possibleUser.isPresent() && possibleUser.get().getPassword().equals(password)) {
            if(loginService.getUserById(userName).getStatus().equals("suspended")){
                return "suspended";
            }
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists
            if (oldSession != null) oldSession.invalidate();

            // generate new session
            HttpSession newSession = request.getSession(true);

            // store user details in session
            newSession.setAttribute("user", possibleUser.get());

            return "redirect:/home";

        } else { // if user details are invalid
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "redirect:/login";
    }
}
