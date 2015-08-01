package ua.com.dog.hotel.dao.order;

import ua.com.dog.hotel.model.order.Order;

import java.util.List;

/**
 * @since: 06.02.15
 * @author: Андрей
 */
public interface OrderDAO {

    void insertOrder(Order order);

    List<Order> selectOrdersByUserId(int userId);

}
