package ua.com.dog.hotel.dao.room;

import ua.com.dog.hotel.model.room.Room;
import ua.com.dog.hotel.model.room.RoomCategory;

import java.util.List;

/**
 * @since: 06.02.15
 * @author: Андрей
 */
public interface RoomDAO {

    Room selectRoomById(int id);

    List<Room> selectAllFreeRooms();

    List<Room> selectAllFreeRooms(int sortValue);

    List<Room> selectAllRoomsByCategory(RoomCategory category);

    List<Room> selectRoomsByBookingRequest(int category, int roomsAmount);

    void updateRoomBusyStateOccupiedById(int id);
}
