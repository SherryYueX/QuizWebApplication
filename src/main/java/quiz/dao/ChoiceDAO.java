package quiz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import quiz.domain.Choice;
import quiz.domain.Question;

import java.util.List;

@Repository
public class ChoiceDAO {
    JdbcTemplate jdbcTemplate;
    ChoiceRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ChoiceDAO(JdbcTemplate jdbcTemplate, ChoiceRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Choice> getAllChoices(){
        String query = "SELECT * FROM choices";

        List<Choice> choices = jdbcTemplate.query(query, rowMapper);

        return choices;
    }

    public List<Choice> getChoicesByQuestionId(int questionId){
        String query = "SELECT * FROM choices WHERE questionId=:questionId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("questionId", questionId);
        List<Choice> choices = namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);

        return choices;
    }

    public Choice getChoiceById(int id){
        String query = "SELECT * FROM choices WHERE id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        List<Choice> choices = namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);

        return choices.size() == 0 ? null : choices.get(0);
    }

    public void createNewChoice(Choice choice){
        String query = "INSERT INTO choices (id, questionId, description) values (?, ?, ?)";
        jdbcTemplate.update(query, choice.getId(), choice.getQuestionId(), choice.getDescription());
    }
}
