package quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quiz.domain.Choice;
import quiz.domain.Question;
import quiz.domain.Result;
import quiz.domain.User;
import quiz.service.QuestionService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class QuizController {

    private QuestionService questionService;

    @Autowired
    public QuizController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/quiz")
    public String getQuiz(HttpSession session, Model model) {
        //user not logged in
        if(!(session != null && session.getAttribute("user") != null)) {
            return "redirect:/login";
        }
        String category = (String)session.getAttribute("categorySelected");
//        System.out.println(category);

        //initializes 5 random questions
        List<Question> questions = questionService.getQuestionsByCategory(category);
        Collections.shuffle(questions);
//        System.out.println(questions.size());
        List<Question> selectedQuestions = new ArrayList<>();
        List<Choice>[] choices = new ArrayList[5];
        for(int i = 0; i < 5; i++){
            selectedQuestions.add(questions.get(i));
        }
        int ind = 0;
        for(Question curr: selectedQuestions){
            choices[ind] = questionService.getChoicesByqid(curr.getId());
            Collections.shuffle(choices[ind++]);
            session.setAttribute("q"+ind+"_ds", curr.getDescription());
        }

        session.setAttribute("choices", choices);
        session.setAttribute("questions", selectedQuestions);
        java.sql.Timestamp start_time = new java.sql.Timestamp(new java.util.Date().getTime());
        session.setAttribute("start_time", start_time);
        return "quiz";
    }

    @PostMapping("/quiz")
    public String submitQuiz(
            @RequestParam(name = "selectedChoiceId1") Integer selectedChoiceId1,
            @RequestParam(name = "selectedChoiceId2") Integer selectedChoiceId2,
            @RequestParam(name = "selectedChoiceId3") Integer selectedChoiceId3,
            @RequestParam(name = "selectedChoiceId4") Integer selectedChoiceId4,
            @RequestParam(name = "selectedChoiceId5") Integer selectedChoiceId5,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        java.sql.Timestamp start_time = (Timestamp) session.getAttribute("start_time");
        java.sql.Timestamp end_time = new java.sql.Timestamp(new java.util.Date().getTime());
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        int correct_count = 0;
        if(questions.get(0).getCorrectChoiceId() == selectedChoiceId1) correct_count++;
        if(questions.get(1).getCorrectChoiceId() == selectedChoiceId2) correct_count++;
        if(questions.get(2).getCorrectChoiceId() == selectedChoiceId3) correct_count++;
        if(questions.get(3).getCorrectChoiceId() == selectedChoiceId4) correct_count++;
        if(questions.get(4).getCorrectChoiceId() == selectedChoiceId5) correct_count++;
        int score = (int)(correct_count/5.0 * 100) ;
        session.setAttribute("user_choice1", questionService.getChoiceById(selectedChoiceId1));
        session.setAttribute("user_choice2", questionService.getChoiceById(selectedChoiceId2));
        session.setAttribute("user_choice3", questionService.getChoiceById(selectedChoiceId3));
        session.setAttribute("user_choice4", questionService.getChoiceById(selectedChoiceId4));
        session.setAttribute("user_choice5", questionService.getChoiceById(selectedChoiceId5));

        int result_id = (start_time.hashCode()+user.getUserName().hashCode()) % 1000;
        Optional<Result> possibleResult = questionService.validateResult(result_id);
        while(possibleResult.isPresent()){
            result_id >>= 1;
            possibleResult = questionService.validateResult(result_id);
        }
        questionService.createResult(new Result(result_id, user.getUserName(), (String)session.getAttribute("categorySelected"),
                start_time,end_time, score, selectedChoiceId1, selectedChoiceId2, selectedChoiceId3,
                selectedChoiceId4, selectedChoiceId5));
        possibleResult = questionService.validateResult(result_id);
        session.setAttribute("result", possibleResult.get());

        return "quiz-result";
    }

    @GetMapping("/quiz-result")
    public String getQuizResult(Model model, HttpSession session) {
        if(session==null || session.getAttribute("user")==null)
            return "redirect:/login";

        if(session.getAttribute("questions")==null || session.getAttribute("choices")==null ||
                session.getAttribute("result")==null)
            return "redirect:/home";

        return "quiz-result";
    }
}
