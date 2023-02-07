package quiz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import quiz.domain.User;

import java.util.List;

@Repository
public class UserDAO {
    JdbcTemplate jdbcTemplate;
    UserRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<User> getAllUsers(){
        String query = "SELECT * FROM users";

        List<User> users = jdbcTemplate.query(query, rowMapper);

        return users;
    }

    public void createNewUser(User user){
        String query = "INSERT INTO users " +
                "(userName, firstName, lastName, emailAddr, physicalAddr, phoneNumber, password, status) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getUserName(), user.getFirstName(), user.getLastName(),
                user.getEmailAddr(), user.getPhysicalAddr(), user.getPhoneNumber(),
                user.getPassword(), user.getStatus());
    }

    public void createNewUser(String userName, String firstName, String lastName,
                              String emailAddr, String physicalAddr, String phoneNumber,
                              String password,String status){
        String query = "INSERT INTO users " +
                "(userName, firstName, lastName, emailAddr, physicalAddr, phoneNumber, password, status) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, userName, firstName, lastName,
                emailAddr, physicalAddr, phoneNumber,
                password, status);
    }

    public User getUserByUserName(String userName){
        String query = "SELECT * FROM users WHERE userName = :userName";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userName", userName);
        List<User> users = namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);

        return users.size() == 0 ? null : users.get(0);
    }

    public void deleteUserByUserName(String userName){
        String query = "DELETE FROM users WHERE userName=?";
        jdbcTemplate.update(query, userName);
    }

    public void updateUserByUserName(String userName, String status){
        String query = "UPDATE users set status=? WHERE userName=?";
        jdbcTemplate.update(query, status, userName);
    }
}
