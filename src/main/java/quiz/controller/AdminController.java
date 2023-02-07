package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import quiz.domain.*;
import quiz.service.QuestionService;
import quiz.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private QuestionService questionService;
    private UserService userService;
    @Autowired
    public AdminController(QuestionService questionService, UserService userService){
        this.questionService = questionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/allResults", method = RequestMethod.GET)
    public String getAllResults(HttpSession session){
        //no user logged in
        if(!(session != null && session.getAttribute("user") != null)) {
            return "redirect:/login";
        }
        //admin
        if(session != null && session.getAttribute("admin") != null){
            List<Result> results = questionService.getAllResults();
            session.setAttribute("allResults", results);
            return "allResults";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/allResults", method = RequestMethod.POST)
    public String postsAllResults(@RequestParam(name = "selectedResultId") int selectedResultId,
                                  HttpSession session, Model model){
        //quiz-result page requires result, choices, questions, user
        Result selectedResult = questionService.getResultById(selectedResultId);
        session.setAttribute("result", selectedResult);
        User selectedUser = userService.getUserById(selectedResult.getUserName());
        session.setAttribute("selectedUser", selectedUser);
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
    @RequestMapping(value = "/addQuiz", method = RequestMethod.GET)
    public String getAddQuiz(HttpSession session){
        //no user logged in
        if(!(session != null && session.getAttribute("user") != null)) {
            return "redirect:/login";
        }
        //admin
        if(session != null && session.getAttribute("admin") != null){
            return "addQuiz";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/addQuiz", method = RequestMethod.POST)
    public String postAddQuiz(@RequestParam String q_des,
                              @RequestParam String category,
                              @RequestParam String choice1,
                              @RequestParam String choice2,
                              @RequestParam String choice3,
                              @RequestParam String choice4,
                              Model model){
        int q_id = q_des.hashCode() % 10000;
//        System.out.println(questionService.validateQuestion(q_id).isPresent());
        while(questionService.validateQuestion(q_id).isPresent()){
            q_id <<= 1;
//            System.out.println(q_id);
        }

        int correct_id = choice1.hashCode() % 10000;
        while(questionService.validateChoice(correct_id).isPresent()){
            correct_id <<= 1;
        }
        questionService.createQuestion(new Question(q_id, category, q_des, correct_id));
        questionService.createChoice(new Choice(correct_id, q_id, choice1));

        int choice_id2 = choice2.hashCode() % 10000;
        while(questionService.validateChoice(choice_id2).isPresent()){
            choice_id2 <<= 1;
        }
        questionService.createChoice(new Choice(choice_id2, q_id, choice2));

        int choice_id3 = choice3.hashCode() % 10000;
        while(questionService.validateChoice(choice_id3).isPresent()){
            choice_id3 <<= 1;
        }
        questionService.createChoice(new Choice(choice_id3, q_id, choice3));

        int choice_id4 = choice4.hashCode() % 10000;
        while(questionService.validateChoice(choice_id4).isPresent()){
            choice_id4 <<= 1;
        }
        questionService.createChoice(new Choice(choice_id4, q_id, choice4));

        return "addQuiz";
    }

    @RequestMapping(value = "/deleteQuestion", method = RequestMethod.GET)
    public String getDeleteQuestion(HttpSession session){
        //no user logged in
        if(!(session != null && session.getAttribute("user") != null)) {
            return "redirect:/login";
        }
        //admin
        if(session != null && session.getAttribute("admin") != null){
            List<Question> questions = questionService.getAllQuestions();
            session.setAttribute("existingQs", questions);
            return "deleteQuestion";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST)
    public String postDeleteQuestion(@RequestParam int selectedQ,
                                     Model model){
        List<Choice> choices = questionService.getChoicesByqid(selectedQ);
        for(Choice c: choices){
            questionService.deleteResultByChoiceId(c.getId());
        }
        questionService.deleteQuestionByQuestionId(selectedQ);
        questionService.deleteChoicesByQuestionId(selectedQ);
        return "redirect:/deleteQuestion";
    }

    @RequestMapping(value = "/allFeedback", method = RequestMethod.GET)
    public String getAllFeedback(HttpSession session){
        //no user logged in
        if(!(session != null && session.getAttribute("user") != null)) {
            return "redirect:/login";
        }
        //admin
        if(session != null && session.getAttribute("admin") != null){
            List<Feedback> feedback = userService.getAllFeedback();
            session.setAttribute("allFeedback", feedback);
            return "allFeedback";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/allMessages", method = RequestMethod.GET)
    public String getAllMessages(HttpSession session){
        //no user logged in
        if(!(session != null && session.getAttribute("user") != null)) {
            return "redirect:/login";
        }
        //admin
        if(session != null && session.getAttribute("admin") != null){
            List<Message> messages = userService.getAllMessages();
            session.setAttribute("allMessages", messages);
            return "allMessages";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public String getAllUsers(HttpSession session){
        //no user logged in
        if(!(session != null && session.getAttribute("user") != null)) {
            return "redirect:/login";
        }
        //admin
        if(session != null && session.getAttribute("admin") != null){
            List<User> users = userService.getAllUsers();
            session.setAttribute("allUsers", users);
            return "allUsers";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.POST, params ="statusAction")
    public String postAllUsers(@RequestParam String statusAction,
                                HttpSession session){
        User selected = userService.getUserById(statusAction);
        System.out.println(statusAction + " " + selected.getStatus());
        if(selected.getStatus().equals("active")){
            userService.updateUserByUserName(statusAction, "suspended");
        }
        else{
            userService.updateUserByUserName(statusAction, "active");
        }
        return "redirect:/allUsers";
    }

}
