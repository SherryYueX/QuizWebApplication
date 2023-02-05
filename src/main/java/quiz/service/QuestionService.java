package quiz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.dao.ChoiceDAO;
import quiz.dao.QuestionDAO;
import quiz.dao.ResultDAO;
import quiz.domain.Choice;
import quiz.domain.Question;
import quiz.domain.Result;
import quiz.domain.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionDAO questionDao;
    private final ChoiceDAO choiceDAO;
    private final ResultDAO resultDAO;

    @Autowired
    public QuestionService(QuestionDAO questionDao, ChoiceDAO choiceDAO, ResultDAO resultDAO) {
        this.questionDao = questionDao;
        this.choiceDAO = choiceDAO;
        this.resultDAO = resultDAO;
    }

    public List<Question> getAllQuestions(){
        return questionDao.getAllQuestions();
    }

    public void createQuestion(Question question) {
        questionDao.createNewQuestion(question);
    }

    public void createChoice(Choice choice) {
        choiceDAO.createNewChoice(choice);
    }

    public void createResult(Result result) {
        resultDAO.createResult(result);
    }

    public Boolean checkAnswer(int id, Choice selectedChoice) {
        Question question = questionDao.getQuestionById(id);
        int correctChoice = question.getCorrectChoiceId();
        return selectedChoice.getId()==correctChoice ? true : false;
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.getQuestionsByCategory(category);
    }

    public List<Choice> getChoicesByqid(int qid) {
        return choiceDAO.getChoicesByQuestionId(qid);
    }

    public Choice getChoiceById(int id){
        return choiceDAO.getChoiceById(id);
    }

    public  Optional<Result> validateResult(int id){
        return resultDAO.getAllResult().stream()
                .filter(a -> a.getId()==id)
                .findAny();
    }

    public Optional<Question> validateQuestion(int id) {
        return questionDao.getAllQuestions().stream()
                .filter(a -> a.getId()==id)
                .findAny();
    }

    public Optional<Choice> validateChoice(int id) {
        return choiceDAO.getAllChoices().stream()
                .filter(a -> a.getId()==id)
                .findAny();
    }
}
