package quiz.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import quiz.domain.Choice;
import quiz.domain.Result;

import java.util.List;

@Repository
public class ResultDAO {
    JdbcTemplate jdbcTemplate;
    ResultRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ResultDAO(JdbcTemplate jdbcTemplate, ResultRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Result> getAllResult(){
        String query = "SELECT * FROM results ORDER BY quiz_start DESC";

        List<Result> results = jdbcTemplate.query(query, rowMapper);

        return results;
    }

    public List<Result> getResultsByUserName(String userName){
        String query = "SELECT * FROM results WHERE userName=:userName";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userName", userName);
        List<Result> results = namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);

        return results;
    }

    public void createResult(Result result){
        String query = "INSERT INTO results (id, userName, quiz_start, quiz_end, score, userChoiceId1," +
                "userChoiceId2, userChoiceId3, userChoiceId4," + "userChoiceId5) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, result.getId(), result.getUserName(), result.getQuiz_start(), result.getQuiz_end(),
                result.getScore(), result.getUserChoiceId1(),result.getUserChoiceId2(),
                result.getUserChoiceId3(),result.getUserChoiceId4(), result.getUserChoiceId5());
    }
}
