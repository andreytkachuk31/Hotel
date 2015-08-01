package ua.com.dog.hotel.dao.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ua.com.dog.hotel.dao.user.UserDAO;
import ua.com.dog.hotel.model.user.User;

import java.util.List;

/**
 * @author Andrii_Tkachuk
 * @since 3/25/2015
 */
@Repository
public class HibernateUserDAOImpl implements UserDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void insertUser(User user) {
        hibernateTemplate.update(user);
    }

    @Override
    @Cacheable("hotelCache")
    public User selectUserByLogin(String login) {
        List<Object> users = hibernateTemplate.find("FROM User WHERE login=?", login);
        if (users.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return (User) users.get(0);
    }

    @Override
    @Cacheable("hotelCache")
    public User selectUserById(int id) {
        return hibernateTemplate.get(User.class, id);
    }

    @Override
    public List<User> selectAllUsers() {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
