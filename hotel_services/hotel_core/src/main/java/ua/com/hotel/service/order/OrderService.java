package ua.com.hotel.service.order;

import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.pagination.PaginatedResults;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
public interface OrderService {

    void makeOrder(Order order);

    PaginatedResults<Order> selectOrdersByUserId(int userId, Pageable pageable);

}
