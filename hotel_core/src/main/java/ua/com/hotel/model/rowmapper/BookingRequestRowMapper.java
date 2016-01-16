package ua.com.hotel.model.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.entity.bookingrequest.BookingRequestStatus;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.entity.room.RoomCategory;
import ua.com.hotel.model.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Андрей
 * @since: 05.09.15
 */
public class BookingRequestRowMapper implements RowMapper<BookingRequest> {

    @Override
    public BookingRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setId(rs.getInt("id"));
        bookingRequest.setRoom(getRoom(rs));
        bookingRequest.setUser(getUser(rs));
        bookingRequest.setRoomsAmount(rs.getInt("rooms_amount"));
        bookingRequest.setDateCheckIn(rs.getDate("date_check_in"));
        bookingRequest.setDateCheckOut(rs.getDate("date_check_out"));
        bookingRequest.setRoomCategory(RoomCategory.valueOf(rs.getInt("category_id")));
        bookingRequest.setStatus(BookingRequestStatus.valueOf(rs.getInt("status")));
        return bookingRequest;
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
