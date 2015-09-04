package ua.com.dog.hotel.dao.room.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.dog.hotel.dao.room.RoomDAO;
import ua.com.dog.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.dog.hotel.model.entity.room.Room;
import ua.com.dog.hotel.model.entity.room.RoomBusyStatus;
import ua.com.dog.hotel.model.pagination.Pageable;
import ua.com.dog.hotel.model.rowmapper.RoomRowMapper;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

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
        return jdbcTemplate.queryForObject("SELECT * FROM rooms WHERE id = ?", new Object[]{id}, new RoomRowMapper());
    }

    @Override
    public List<Room> selectAllRooms() {
        return jdbcTemplate.query("SELECT * FROM rooms", new RoomRowMapper());
    }

    @Override
    public List<Room> selectAllFreeRooms(Pageable pageable) {
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
            whereAnd.append("AND number LIKE '%" + filter + "%'")
                    .append(" OR place_amount LIKE '%" + filter + "%'")
                    .append(" OR price LIKE '%" + filter + "%'");

        }
        return jdbcTemplate.query("SELECT * FROM rooms WHERE busy_status = ? " + whereAnd + " " + orderBy + " LIMIT ? OFFSET ?",
                new Object[]{RoomBusyStatus.FREE.getStatusId(), limit, offset},
                new RoomRowMapper());
    }

    @Override
    public int selectCountAllFreeRooms() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM rooms WHERE busy_status = ?",
                new Object[]{RoomBusyStatus.FREE.getStatusId()},
                Integer.class);
    }

    @Override
    public void updateRoomBusyStatusBookedById(int id) {
        jdbcTemplate.update("UPDATE rooms SET busy_status = ? WHERE id = ?", RoomBusyStatus.BOOKED.getStatusId(), id);
    }

    @Override
    public List<Room> selectFreeRoomsByBookingRequest(BookingRequest bookingRequest) {
        return jdbcTemplate.query(
                 "SELECT * FROM rooms WHERE busy_status=? AND category_id=? AND place_amount=?",
                new Object[]{RoomBusyStatus.FREE.getStatusId(), bookingRequest.getCategoryId(), bookingRequest.getRoomsAmount()},
                new RoomRowMapper());
    }
}
