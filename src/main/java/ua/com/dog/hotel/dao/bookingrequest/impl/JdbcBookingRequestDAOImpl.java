package ua.com.dog.hotel.dao.bookingrequest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.dog.hotel.dao.bookingrequest.BookingRequestDAO;
import ua.com.dog.hotel.model.bookingrequest.BookingRequest;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
@Repository
public class JdbcBookingRequestDAOImpl implements BookingRequestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertBookingRequest(BookingRequest bookingRequest) {
        jdbcTemplate.update(
                "INSERT INTO booking_requests (rooms_amount, date_check_in, date_check_out, user_id, category_id, status_id, room_id) VALUES(?,?,?,?,?,?,?)",
                bookingRequest.getRoomsAmount(),
                bookingRequest.getDateCheckIn(),
                bookingRequest.getDateCheckOut(),
                bookingRequest.getUserId(),
                bookingRequest.getCategoryId(),
                bookingRequest.getStatusId(),
                bookingRequest.getRoomId()
        );
    }

    @Override
    public List<BookingRequest> selectBookingRequestsByUserId(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM booking_requests INNER JOIN users ON users.id = booking_requests.user_id WHERE user_id=?",
                new Object[]{userId},
                new BeanPropertyRowMapper<BookingRequest>(BookingRequest.class)
        );
    }
}
