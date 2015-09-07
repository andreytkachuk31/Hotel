package ua.com.hotel.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hotel.dao.order.OrderDAO;
import ua.com.hotel.dao.room.RoomDAO;
import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.service.order.OrderService;

import java.util.List;

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
    private RoomDAO roomDAO;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void makeOrder(Order order) {
        roomDAO.updateRoomBusyStatusBookedById(order.getRoom().getId());
        orderDAO.insertOrder(order);
    }

    @Override
    public List<Order> selectOrdersByUserId(int userId) {
        return orderDAO.selectOrdersByUserId(userId);
    }
}
