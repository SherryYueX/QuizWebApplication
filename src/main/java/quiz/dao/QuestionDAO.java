package quiz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import quiz.domain.Choice;
import quiz.domain.Question;
import quiz.domain.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDAO {
    JdbcTemplate jdbcTemplate;
    QuestionRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestionDAO(JdbcTemplate jdbcTemplate, QuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Question> getAllQuestions(){
        String query = "SELECT * FROM questions";

        List<Question> questions = jdbcTemplate.query(query, rowMapper);

        return questions;
    }

    public List<Question> getQuestionsByCategory(String category){
        String query = "SELECT * FROM questions WHERE category=:category";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("category", category);
        List<Question> questions = namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);

        return questions;
    }

    public Question getQuestionById(int id){
        String query = "SELECT * FROM questions WHERE id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        List<Question> questions = namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);

        return questions.size() == 0 ? null : questions.get(0);
    }

    public void createNewQuestion(Question question){
        String query = "INSERT INTO questions (id, category, description, correctChoiceId) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, question.getId(), question.getCategory(), question.getDescription(), question.getCorrectChoiceId());
    }
}
