package quiz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import quiz.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserName(rs.getString("userName"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmailAddr(rs.getString("emailAddr"));
        user.setPhysicalAddr(rs.getString("physicalAddr"));
        user.setPhoneNumber(rs.getString("phoneNumber"));
        user.setPassword(rs.getString("password"));
        user.setStatus(rs.getString("status"));
        return user;
    }
}
