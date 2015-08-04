package ua.com.dog.hotel.dao.room.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.dog.hotel.dao.room.RoomDAO;
import ua.com.dog.hotel.model.room.Room;
import ua.com.dog.hotel.model.room.RoomCategory;
import ua.com.dog.hotel.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @since: 06.02.15
 * @author: Андрей
 */
@Repository
public class JdbcRoomDAOImpl implements RoomDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Room selectRoomById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM rooms WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<Room>(Room.class));
    }

    @Override
    public List<Room> selectAllFreeRooms() {
        return jdbcTemplate.query("SELECT * FROM rooms WHERE busy_state = 0", new BeanPropertyRowMapper<Room>(Room.class));
    }

    @Override
    public List<Room> selectAllFreeRooms(int sortValue) {
        return null;
    }

    @Override
    public List<Room> selectAllRoomsByCategory(RoomCategory category) {
        return null;
    }

    @Override
    public List<Room> selectRoomsByBookingRequest(int category, int roomsAmount) {
        return null;
    }

    @Override
    public void updateRoomBusyStateOccupiedById(int id) {
        jdbcTemplate.update("UPDATE rooms SET busy_state = 2 WHERE id = ?", id);
    }
}
