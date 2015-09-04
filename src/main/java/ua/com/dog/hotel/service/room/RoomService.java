package ua.com.dog.hotel.service.room;

import ua.com.dog.hotel.model.pagination.PaginatedResults;
import ua.com.dog.hotel.model.entity.room.Room;
import ua.com.dog.hotel.model.pagination.Pageable;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
public interface RoomService {

    Room selectRoomById(int id);

    List<Room> selectAllRooms();

    PaginatedResults<Room> selectAllFreeRooms(Pageable pageable);

    List<Room> selectFreeRoomsByBookingRequestId(int bookingRequestId);

    void updateRoomBusyStatusBookedById(int id);
}
