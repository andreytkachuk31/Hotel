package ua.com.hotel.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hotel.dao.order.OrderDAO;
import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.exception.RoomAlreadyBookedException;
import ua.com.hotel.model.exception.RoomNotFoundException;
import ua.com.hotel.service.order.OrderService;
import ua.com.hotel.service.room.RoomService;

import java.util.List;

import static ua.com.hotel.model.entity.room.RoomBusyStatus.BOOKED;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Qualifier("jdbcOrderDAOImpl")
    private OrderDAO orderDAO;

    @Autowired
    private RoomService roomService;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void makeOrder(Order order) {
        Room roomFromOrder = order.getRoom();

        if (isNotExistRoom(roomFromOrder))
            throw new RoomNotFoundException("Room =" + roomFromOrder + " not found for order " + order);

        int roomId = roomFromOrder.getId();
        Room room = roomService.selectRoomById(roomId);

        if (isNotExistRoom(room))
            throw new RoomNotFoundException("Room =" + room + " not found for order " + order);
        else if (BOOKED.equals(room.getBusyStatus()))
            throw new RoomAlreadyBookedException("Room " + room + " already exist for order " + order);

        roomService.updateRoomBusyStatusBookedById(roomId);
        orderDAO.insertOrder(order);
    }

    @Override
    public List<Order> selectOrdersByUserId(int userId) {
        return orderDAO.selectOrdersByUserId(userId);
    }

    private boolean isNotExistRoom(Room room) {
        return room == null;
    }
}
