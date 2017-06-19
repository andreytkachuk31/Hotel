package ua.com.hotel.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hotel.dao.user.UserDAO;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.entity.user.UserPrincipal;
import ua.com.hotel.model.entity.user.UserRole;
import ua.com.hotel.model.entity.user.UserStatus;
import ua.com.hotel.service.user.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 21.05.13
 *
 * @author: andrey.tkachuk31
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    @Qualifier("jdbcUserDAOImpl")
    private UserDAO userDAO;

    @Override
    public boolean login(String login, String password) {
        User user = selectUserByLogin(login);
        return user != null && user.isActive() && user.getPassword().equals(password);
    }

    @Override
    public void register(User user) {
        user.setRole(UserRole.ROLE_CLIENT);
        insertUser(user);
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
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void blockUser(int id) {
        User user = selectUserById(id);
        user.setStatus(UserStatus.BLOCKED);
        userDAO.updateUser(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void unBlockUser(int id) {
        User user = selectUserById(id);
        user.setStatus(UserStatus.ACTIVE);
        userDAO.updateUser(user);
    }

    @Override
    public boolean isUserExist(String login) {
        return selectUserByLogin(login) != null;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = selectUserByLogin(username);

        if (user == null || !user.isActive())
            throw new UsernameNotFoundException("User isn't exist or isn't active");

        return buildUserPrincipalFromUser(user);
    }

    private UserPrincipal buildUserPrincipalFromUser(User user) {
        return new UserPrincipal(user, user.getLogin(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
