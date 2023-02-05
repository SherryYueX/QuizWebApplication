package quiz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import quiz.domain.Choice;
import quiz.domain.Result;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ResultRowMapper implements RowMapper<Result> {
    @Override
    public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
        Result result = new Result();
        result.setId(rs.getInt("id"));
        result.setUserName(rs.getString("userName"));
        result.setQuiz_start(rs.getTimestamp("quiz_start"));
        result.setQuiz_end(rs.getTimestamp("quiz_end"));
        result.setScore(rs.getInt("score"));
        result.setUserChoiceId1(rs.getInt("userChoiceId1"));
        result.setUserChoiceId2(rs.getInt("userChoiceId2"));
        result.setUserChoiceId3(rs.getInt("userChoiceId3"));
        result.setUserChoiceId4(rs.getInt("userChoiceId4"));
        result.setUserChoiceId5(rs.getInt("userChoiceId5"));
        return result;
    }
}
