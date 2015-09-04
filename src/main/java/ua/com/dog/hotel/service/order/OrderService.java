package ua.com.dog.hotel.service.order;

import ua.com.dog.hotel.model.entity.order.Order;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
public interface OrderService {

    void makeOrder(Order order);

    List<Order> selectOrdersByUserId(int userId);

}
