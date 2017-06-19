package ua.com.hotel.service.user;

import ua.com.hotel.model.entity.user.User;

import java.util.List;

/**
 * Date: 20.05.13
 *
 * @author: andrey.tkachuk31
 */
public interface UserService {

    boolean login(String login, String password);

    void register(User user);

    User selectUserByLogin(String login);

    User selectUserById(int id);

    List<User> selectAllUsers();

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    void blockUser(int id);

    void unBlockUser(int id);

    boolean isUserExist(String login);
}
