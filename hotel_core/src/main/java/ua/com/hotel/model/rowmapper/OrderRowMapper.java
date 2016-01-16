package ua.com.hotel.model.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Andrii_Tkachuk
 * @since 9/4/2015
 */
public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setRoom(getRoom(rs));
        order.setUser(getUser(rs));
        order.setDateCheckIn(rs.getDate("date_check_in"));
        order.setDateCheckOut(rs.getDate("date_check_out"));
        order.setBill(rs.getInt("bill"));
        return order;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        return user;
    }

    private Room getRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("room_id"));
        return room;
    }
}
