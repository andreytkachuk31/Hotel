package ua.com.hotel.dao.user;

import ua.com.hotel.model.entity.user.User;

import java.util.List;

/**
 * Date: 20.05.13
 *
 * @author: andrey.tkachuk31
 */
public interface UserDAO {

    void insertUser(User user);

    User selectUserByLogin(String login);

    User selectUserById(int id);

    List<User> selectAllUsers();

    void updateUser(User user);

    void deleteUser(int id);
}
