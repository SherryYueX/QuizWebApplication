package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import quiz.domain.*;
import quiz.service.QuestionService;
import quiz.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private QuestionService questionService;
    private UserService userService;

    @Autowired
    public HomeController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(HttpSession session, Model model) {
        if(session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            //userResults
            List<Result> userResults = questionService.getResultsByUserName(user.getUserName());
            session.setAttribute("userResults", userResults);
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

    @RequestMapping(value = "/home", method = RequestMethod.POST, params ="selectedResultId")
    public String postResult(@RequestParam(name = "selectedResultId") int selectedResultId,
                             HttpSession session, Model model) {
        //quiz-result page requires result, choices, questions, user
        session.setAttribute("selectedUser", session.getAttribute("user"));
        Result selectedResult = questionService.getResultById(selectedResultId);
        session.setAttribute("result", selectedResult);
        Choice user_choice1 = questionService.getChoiceById(selectedResult.getUserChoiceId1());
        Choice user_choice2 = questionService.getChoiceById(selectedResult.getUserChoiceId2());
        Choice user_choice3 = questionService.getChoiceById(selectedResult.getUserChoiceId3());
        Choice user_choice4 = questionService.getChoiceById(selectedResult.getUserChoiceId4());
        Choice user_choice5 = questionService.getChoiceById(selectedResult.getUserChoiceId5());
        session.setAttribute("user_choice1", user_choice1);
        session.setAttribute("user_choice2", user_choice2);
        session.setAttribute("user_choice3", user_choice3);
        session.setAttribute("user_choice4", user_choice4);
        session.setAttribute("user_choice5", user_choice5);

        List<Question> questions = new ArrayList<>();
        questions.add(questionService.getQuestionById(user_choice1.getQuestionId()));
        questions.add(questionService.getQuestionById(user_choice2.getQuestionId()));
        questions.add(questionService.getQuestionById(user_choice3.getQuestionId()));
        questions.add(questionService.getQuestionById(user_choice4.getQuestionId()));
        questions.add(questionService.getQuestionById(user_choice5.getQuestionId()));
        session.setAttribute("questions", questions);

        List<Choice>[] choices = new ArrayList[5];
        choices[0] = questionService.getChoicesByqid(questions.get(0).getId());
        choices[1] = questionService.getChoicesByqid(questions.get(1).getId());
        choices[2] = questionService.getChoicesByqid(questions.get(2).getId());
        choices[3] = questionService.getChoicesByqid(questions.get(3).getId());
        choices[4] = questionService.getChoicesByqid(questions.get(4).getId());
        session.setAttribute("choices", choices);
        return "quiz-result";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String getFeedback(HttpSession session){
        if(session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            return "feedback";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public String postFeedback(@RequestParam int rating,
                               @RequestParam String userFeedback,
                               HttpSession session){
        int id = userFeedback.hashCode() % 1000;
        while(userService.validateFeedback(id).isPresent()){
            id <<= 1;
        }
        userService.createFeedback(new Feedback(id, rating, userFeedback));
        return "redirect:/feedback";
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String getMessage(HttpSession session){
        if(session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            return "message";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public String postMessage(@RequestParam String userMessage,
                               HttpSession session){
        int id = userMessage.hashCode() % 1000;
        java.sql.Timestamp sent_time = new java.sql.Timestamp(new java.util.Date().getTime());
        while(userService.validateMessage(id).isPresent()){
            id <<= 1;
        }
        User curr = (User)session.getAttribute("user");
        userService.createMessage(new Message(id, curr.getUserName(), sent_time, userMessage));
        return "redirect:/message";
    }

}
