package quiz.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("user") != null) {
            model.addAttribute("login", 1);
            return "home";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST, params ="cat")
    public String postCat(HttpSession session, Model model) {
        session.setAttribute("categorySelected", "cat");
        return "redirect:/quiz";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST, params ="leetcode")
    public String postLeetcode(HttpSession session, Model model) {
        session.setAttribute("categorySelected", "leetcode");
        return "redirect:/quiz";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST, params ="horror")
    public String postHorror(HttpSession session, Model model) {
        session.setAttribute("categorySelected", "horror");
        return "redirect:/quiz";
    }
}
