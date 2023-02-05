package quiz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import quiz.domain.Choice;
import quiz.domain.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChoiceRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Choice choice = new Choice();
        choice.setId(rs.getInt("id"));
        choice.setQuestionId(rs.getInt("questionId"));
        choice.setDescription(rs.getString("description"));
        return choice;
    }
}
