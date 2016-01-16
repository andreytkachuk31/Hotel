package ua.com.hotel.dao.room;

import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.pagination.Pageable;

import java.util.List;

/**
 * @since: 06.02.15
 * @author: Андрей
 */
public interface RoomDAO {

    Room selectRoomById(int id);

    List<Room> selectAllRooms();

    List<Room> selectAllFreeRooms(Pageable pageable);

    int selectCountAllFreeRooms();

    void updateRoomBusyStatusBookedById(int id);

    List<Room> selectFreeRoomsByBookingRequest(BookingRequest bookingRequest);
}
