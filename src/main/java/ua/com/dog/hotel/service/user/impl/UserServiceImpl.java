package ua.com.dog.hotel.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.dog.hotel.dao.user.UserDAO;
import ua.com.dog.hotel.model.user.UserRole;
import ua.com.dog.hotel.model.user.User;
import ua.com.dog.hotel.model.user.UserStatus;
import ua.com.dog.hotel.service.user.UserService;

import java.util.List;

/**
 * Date: 21.05.13
 *
 * @author: andrey.tkachuk31
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("jdbcUserDAOImpl")
    private UserDAO userDAO;

    @Override
    public boolean login(String login, String password) {
        User user = selectUserByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User selectUserByLogin(String login) {
        try {
            return userDAO.selectUserByLogin(login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User selectUserById(int id) {
        return userDAO.selectUserById(id);
    }

    @Override
    public List<User> selectAllUsers() {
        return userDAO.selectAllUsers();
    }

    @Override
    public void register(User user) {
        user.setRoleId(UserRole.CLIENT.getRoleId());
        user.setStatusId(UserStatus.ACTIVE.getStatusId());
        insertUser(user);
    }

    @Override
    public boolean isUserDeleted(User user) {
        return UserStatus.DELETED.equals(UserStatus.valueOf(user.getStatusId()));
    }

    @Override
    public boolean isUserBlocked(User user) {
        return UserStatus.BLOCKED.equals(UserStatus.valueOf(user.getStatusId()));
    }

    @Override
    public boolean isUserExist(String login) {
        return selectUserByLogin(login) != null;
    }
}
