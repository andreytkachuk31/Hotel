package ua.com.dog.hotel.dao.bookingrequest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.dog.hotel.dao.bookingrequest.BookingRequestDAO;
import ua.com.dog.hotel.model.entity.bookingrequest.BookingRequest;

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
    public BookingRequest selectBookingRequestsById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM booking_requests WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<BookingRequest>(BookingRequest.class));
    }

    @Override
    public List<BookingRequest> selectBookingRequestsByUserId(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM booking_requests WHERE user_id=?",
                new Object[]{userId},
                new BeanPropertyRowMapper<BookingRequest>(BookingRequest.class)
        );
    }

    @Override
    public List<BookingRequest> selectAllBookingRequests() {
        return jdbcTemplate.query(
                "SELECT * FROM booking_requests",
                new BeanPropertyRowMapper<BookingRequest>(BookingRequest.class));
    }

    @Override
    public void updateBookingRequestStatusById(int id) {
        jdbcTemplate.update("UPDATE booking_requests SET status_id = 3 WHERE id = ?", id);
    }
}
