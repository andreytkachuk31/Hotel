package ua.com.hotel.model.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.entity.room.RoomBusyStatus;
import ua.com.hotel.model.entity.room.RoomCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Andrii_Tkachuk
 * @since 9/4/2015
 */
public class RoomRowMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setNumber(rs.getInt("number"));
        room.setPlaceAmount(rs.getInt("place_amount"));
        room.setRoomCategory(RoomCategory.valueOf(rs.getInt("category_id")));
        room.setPrice(rs.getInt("price"));
        room.setBusyStatus(RoomBusyStatus.valueOf(rs.getInt("busy_status")));
        return room;
    }
}
