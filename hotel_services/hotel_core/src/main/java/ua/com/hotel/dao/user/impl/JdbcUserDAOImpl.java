package ua.com.hotel.dao.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.hotel.dao.user.UserDAO;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.rowmapper.UserRowMapper;

import java.util.List;

/**
 * @since: 06.02.15
 * @author: Андрей
 */
@Repository
public class JdbcUserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO users(first_name, last_name, login, password, role_id) VALUES (?,?,?,?,?)",
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getPassword(),
                user.getRole().getRoleId());
    }

    @Override
    @Cacheable("hotelCache")
    public User selectUserByLogin(String login) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE login=?", new Object[]{login}, new UserRowMapper());
    }

    @Override
    @Cacheable("hotelCache")
    public User selectUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[]{id}, new UserRowMapper());
    }

    @Override
    @Cacheable("hotelCache")
    public List<User> selectAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(
                "UPDATE users SET first_name=?, last_name=?, fail_login_attempt=?, role_id=?, status_id=? WHERE id=?",
                user.getFirstName(),
                user.getLastName(),
                user.getFailLoginAttempt(),
                user.getRole().getRoleId(),
                user.getStatus().getStatusId(),
                user.getId());
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }
}
