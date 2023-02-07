package quiz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import quiz.domain.Feedback;
import quiz.domain.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(rs.getInt("id"));
        message.setUsername(rs.getString("username"));
        message.setSent_time(rs.getTimestamp("sent_time"));
        message.setContent(rs.getString("content"));
        return message;
    }
}
