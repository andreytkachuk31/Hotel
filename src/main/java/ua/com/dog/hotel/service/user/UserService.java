package ua.com.dog.hotel.service.user;

import ua.com.dog.hotel.model.entity.user.User;

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

    void deleteUser(int id);

    void blockUser(int id);

    void unBlockUser(int id);

    User selectUserByLogin(String login);

    User selectUserById(int id);

    List<User> selectAllUsers();

    boolean isUserExist(String login);
}
