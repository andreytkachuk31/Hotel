package ua.com.dog.hotel.dao.user.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.dog.hotel.dao.user.UserDAO;
import ua.com.dog.hotel.model.user.User;

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
                user.getRoleId());
    }

    @Override
    @Cacheable("hotelCache")
    public User selectUserByLogin(String login) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE login=?", new Object[]{login}, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    @Cacheable("hotelCache")
    public User selectUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    @Cacheable("hotelCache")
    public List<User> selectAllUsers() {
        final String SQL__FIND_ALL_USERS = "SELECT * FROM users WHERE status_id <> 2";
        return null;
    }

    @Override
    public void updateUser(User user) {
        final String SQL__UPDATE_USER = "UPDATE users SET first_name=?," +
                "last_name=?, password=?, email=?, role_id=?, status_id=?, loginFailed=? WHERE login=?";
    }

    @Override
    public void deleteUser(User user) {
        final String SQL__DELETE_USER = "UPDATE users SET status_id = 2 WHERE id = ?";
    }
}
