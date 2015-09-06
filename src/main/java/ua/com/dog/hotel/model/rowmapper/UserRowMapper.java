package ua.com.dog.hotel.model.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.dog.hotel.model.entity.room.Room;
import ua.com.dog.hotel.model.entity.room.RoomBusyStatus;
import ua.com.dog.hotel.model.entity.room.RoomCategory;
import ua.com.dog.hotel.model.entity.user.User;
import ua.com.dog.hotel.model.entity.user.UserRole;
import ua.com.dog.hotel.model.entity.user.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Andrii_Tkachuk
 * @since 9/4/2015
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(UserRole.valueOf(rs.getInt("role_id")));
        user.setStatus(UserStatus.valueOf(rs.getInt("status_id")));
        return user;
    }
}
