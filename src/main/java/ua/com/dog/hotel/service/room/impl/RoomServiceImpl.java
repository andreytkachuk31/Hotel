package ua.com.dog.hotel.service.room.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.dog.hotel.dao.room.RoomDAO;
import ua.com.dog.hotel.model.room.Room;
import ua.com.dog.hotel.model.room.RoomCategory;
import ua.com.dog.hotel.service.room.RoomService;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDAO roomDAO;

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
        return roomDAO.selectAllFreeRooms();
    }

    @Override
    public List<Room> selectAllFreeRooms() {
        return roomDAO.selectAllFreeRooms();
    }

    @Override
    public List<Room> selectAllFreeRooms(int sortValue) {
        return roomDAO.selectAllFreeRooms(sortValue);
    }

    @Override
    public List<Room> selectAllRoomsByCategory(RoomCategory category) {
        return roomDAO.selectAllRoomsByCategory(category);
    }

    @Override
    public List<Room> selectRoomsByBookingRequest(int category, int roomsAmount) {
        return roomDAO.selectRoomsByBookingRequest(category, roomsAmount);
    }

    @Override
    public void updateRoomBusyStateOccupiedById(int id) {
        roomDAO.updateRoomBusyStateOccupiedById(id);
    }
}
