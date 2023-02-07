package quiz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import quiz.domain.Feedback;
import quiz.domain.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackMapper implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getInt("id"));
        feedback.setRating(rs.getInt("rating"));
        feedback.setContent(rs.getString("content"));
        return feedback;
    }
}
