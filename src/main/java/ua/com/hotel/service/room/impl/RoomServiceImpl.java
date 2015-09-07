package ua.com.hotel.service.room.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.hotel.dao.bookingrequest.BookingRequestDAO;
import ua.com.hotel.dao.room.RoomDAO;
import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.pagination.PaginatedResults;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.service.room.RoomService;
import ua.com.hotel.model.pagination.Pageable;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private BookingRequestDAO bookingRequestDAO;

    @Override
    public Room selectRoomById(int id) {
        try {
            return roomDAO.selectRoomById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Room> selectAllRooms() {
        return roomDAO.selectAllRooms();
    }

    @Override
    public PaginatedResults<Room> selectAllFreeRooms(Pageable pageable) {
        List<Room> freeRooms = roomDAO.selectAllFreeRooms(pageable);
        int totalCount = roomDAO.selectCountAllFreeRooms();
        int numberOfPages = (totalCount / pageable.getPerPage()) + 1;
        return new PaginatedResults<Room>(numberOfPages, totalCount, freeRooms);
    }

    @Override
    public List<Room> selectFreeRoomsByBookingRequestId(int bookingRequestId) {
        BookingRequest bookingRequest = bookingRequestDAO.selectBookingRequestsById(bookingRequestId);
        return roomDAO.selectFreeRoomsByBookingRequest(bookingRequest);
    }

    @Override
    public void updateRoomBusyStatusBookedById(int id) {
        roomDAO.updateRoomBusyStatusBookedById(id);
    }
}
