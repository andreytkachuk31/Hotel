package ua.com.dog.hotel.service.user;

import ua.com.dog.hotel.model.user.User;

import java.util.List;

/**
 * Date: 20.05.13
 *
 * @author: andrey.tkachuk31
 */
public interface UserService {

    void register(User user);

    boolean login(String login, String password);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User selectUserByLogin(String login);

    User selectUserById(int id);

    List<User> selectAllUsers();

    boolean isUserExist(String login);

    boolean isUserDeleted(User user);

    boolean isUserBlocked(User user);
}
