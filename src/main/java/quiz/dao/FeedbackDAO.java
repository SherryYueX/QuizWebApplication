package quiz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import quiz.domain.Choice;
import quiz.domain.Feedback;

import java.util.List;

@Repository
public class FeedbackDAO {
    JdbcTemplate jdbcTemplate;
    FeedbackMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FeedbackDAO(JdbcTemplate jdbcTemplate, FeedbackMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Feedback> getAllFeedback(){
        String query = "SELECT * FROM feedback";
        List<Feedback> feedback = jdbcTemplate.query(query, rowMapper);
        return feedback;
    }

    public void createFeedback(Feedback feedback){
        String query = "INSERT INTO feedback (id, rating, content) values (?,?,?)";
        jdbcTemplate.update(query, feedback.getId(), feedback.getRating(), feedback.getContent());
    }
}
