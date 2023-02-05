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
        String category = (String)session.getAttribute("categorySelected");
//        System.out.println(category);
        List<Question> questions = questionService.getQuestionsByCategory(category);

        //initializes 5 random questions
        List<Question> selectedQuestions = new ArrayList<>();
        List<Choice>[] choices = new ArrayList[5];
        Set<Integer> generatedInd = new HashSet<>();
        while(generatedInd.size() < 5){
            generatedInd.add((int)Math.floor(Math.random()*questions.size()));
        }
        int ind = 0;
        for(int i: generatedInd){
            Question curr = questions.get(i);
            choices[ind++] = questionService.getChoicesByqid(curr.getId());
            selectedQuestions.add(curr);
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
        // selectedChoiceId is assumed to be non-null
//        result.getUserName(), result.getQuiz_start(), result.getQuiz_end(), result.getScore(),
//                result.getQuestionId1(),result.getUserChoiceId1(),result.getQuestionId2(),result.getUserChoiceId2(),
//                result.getQuestionId3(),result.getUserChoiceId3(),result.getQuestionId4(),result.getUserChoiceId4(),
//                result.getQuestionId5(),result.getUserChoiceId5());
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
        questionService.createResult(new Result(result_id, user.getUserName(), start_time,end_time, score,
                selectedChoiceId1, selectedChoiceId2, selectedChoiceId3, selectedChoiceId4, selectedChoiceId5));
        possibleResult = questionService.validateResult(result_id);
        session.setAttribute("result", possibleResult.get());

        return "quiz-result";
    }

    @RequestMapping(value = "/addQuiz", method = RequestMethod.GET)
    public String getAddQuiz(){
        return "addQuiz";
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
        Optional<Question> possibleQuestion = questionService.validateQuestion(q_id);
        while(possibleQuestion.isPresent()){
            q_id >>= 1;
            possibleQuestion = questionService.validateQuestion(q_id);
        }
        int correct_id = choice1.hashCode() % 10000;
        Optional<Choice> possibleChoice = questionService.validateChoice(correct_id);
        while(possibleChoice.isPresent()){
            correct_id >>= 1;
            possibleChoice = questionService.validateChoice(correct_id);
        }
        int choice_id2 = choice2.hashCode() % 10000;
        possibleChoice = questionService.validateChoice(choice_id2);
        while(possibleChoice.isPresent()){
            choice_id2 >>= 1;
            possibleChoice = questionService.validateChoice(choice_id2);
        }
        int choice_id3 = choice3.hashCode() % 10000;
        possibleChoice = questionService.validateChoice(choice_id3);
        while(possibleChoice.isPresent()){
            choice_id3 >>= 1;
            possibleChoice = questionService.validateChoice(choice_id3);
        }
        int choice_id4 = choice4.hashCode() % 10000;
        possibleChoice = questionService.validateChoice(choice_id4);
        while(possibleChoice.isPresent()){
            choice_id4 >>= 1;
            possibleChoice = questionService.validateChoice(choice_id4);
        }
        questionService.createQuestion(new Question(q_id, category, q_des, correct_id));
        questionService.createChoice(new Choice(correct_id, q_id, choice1));
        questionService.createChoice(new Choice(choice_id2, q_id, choice2));
        questionService.createChoice(new Choice(choice_id3, q_id, choice3));
        questionService.createChoice(new Choice(choice_id4, q_id, choice4));
        return "addQuiz";
    }

//    @GetMapping("/quiz-result")
//    public String getQuizResult(Model model, HttpSession session) {
//
//        Integer selectedChoiceId = (Integer) session.getAttribute("selectedChoiceId");
//
//        Optional<Choice> selectedChoice = questionService.getChoiceById(selectedChoiceId);
//
//        if (selectedChoice.isPresent()) {
//            String result = questionService.checkAnswer(selectedChoice.get());
//            model.addAttribute("selectedChoiceDescription", selectedChoice.get().getDescription());
//            model.addAttribute("result", result);
//        } else {
//            model.addAttribute("result", "Invalid choice");
//        }
//        return "quiz-result";
//    }
}
