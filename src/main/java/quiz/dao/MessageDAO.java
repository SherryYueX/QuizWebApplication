package quiz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import quiz.domain.Message;

import java.util.List;

@Repository
public class MessageDAO {
    JdbcTemplate jdbcTemplate;
    MessageRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public MessageDAO(JdbcTemplate jdbcTemplate, MessageRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Message> getAllMessages(){
        String query = "SELECT * FROM messages ORDER BY sent_time";
        List<Message> messages = jdbcTemplate.query(query, rowMapper);
        return messages;
    }

    public void createMessage(Message message){
        String query = "INSERT INTO messages (id, username, sent_time, content) values(?,?,?,?)";
        jdbcTemplate.update(query, message.getId(), message.getUsername(), message.getSent_time(), message.getContent());
    }
}
