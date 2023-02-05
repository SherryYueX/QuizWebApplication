package quiz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import quiz.domain.Question;
import quiz.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setCategory(rs.getString("category"));
        question.setDescription(rs.getString("description"));
        question.setCorrectChoiceId(rs.getInt("correctChoiceId"));
        return question;
    }
}
