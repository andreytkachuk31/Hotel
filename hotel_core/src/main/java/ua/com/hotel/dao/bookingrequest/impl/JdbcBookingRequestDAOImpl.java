package ua.com.hotel.dao.bookingrequest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.hotel.dao.bookingrequest.BookingRequestDAO;
import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.entity.bookingrequest.BookingRequestStatus;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.rowmapper.BookingRequestRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
                "INSERT INTO booking_requests (rooms_amount, date_check_in, date_check_out, user_id, category_id, status, room_id) VALUES(?,?,?,?,?,?,?)",
                bookingRequest.getRoomsAmount(),
                bookingRequest.getDateCheckIn(),
                bookingRequest.getDateCheckOut(),
                bookingRequest.getUser().getId(),
                bookingRequest.getRoomCategory().getValue(),
                bookingRequest.getStatus().getValue(),
                bookingRequest.getRoom() != null ? bookingRequest.getRoom().getId() : 0
        );
    }

    @Override
    public BookingRequest selectBookingRequestsById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM booking_requests WHERE id=?",
                new Object[]{id},
                new BookingRequestRowMapper());
    }

    @Override
    public List<BookingRequest> selectBookingRequestsByUserId(int userId, Pageable pageable) {
        int limit = pageable.getPerPage();
        int offset = (pageable.getPage() - 1) * limit;
        String sort = pageable.getSort();
        String filter = pageable.getFilter();

        StringBuilder orderBy = new StringBuilder(EMPTY);
        if (isNotBlank(sort)) {
            orderBy.append("ORDER BY " + sort + " ASC");
        }
        StringBuilder whereAnd = new StringBuilder(EMPTY);
        if (isNotBlank(filter)) {
            whereAnd.append("AND rooms_amount LIKE '%" + filter + "%'")
                    .append(" OR status LIKE '%" + filter + "%'");
        }

        return jdbcTemplate.query(
                "SELECT * FROM booking_requests WHERE user_id=? " + whereAnd + " " + orderBy + " LIMIT ? OFFSET ?",
                new Object[]{userId, limit, offset},
                new BookingRequestRowMapper());
    }

    @Override
    public int selectCountBookingRequestsByUserId(int userId) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM booking_requests WHERE user_id=?",
                new Object[]{userId},
                Integer.class);
    }

    @Override
    public List<BookingRequest> selectAllBookingRequests() {
        return jdbcTemplate.query(
                "SELECT * FROM booking_requests INNER JOIN users ON users.id = booking_requests.user_id",
                new RowMapper<BookingRequest>() {
                    @Override
                    public BookingRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
                        BookingRequest bookingRequest = new BookingRequestRowMapper().mapRow(rs, rowNum);
                        User user = bookingRequest.getUser();
                        user.setLogin(rs.getString("login"));
                        return bookingRequest;
                    }
                });
    }

    @Override
    public void updateBookingRequestStatusById(int id) {
        jdbcTemplate.update("UPDATE booking_requests SET status = ? WHERE id = ?", BookingRequestStatus.BOOKED.getValue(), id);
    }
}
