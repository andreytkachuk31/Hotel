package ua.com.hotel.dao.order;

import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.pagination.Pageable;

import java.util.List;

/**
 * @since: 06.02.15
 * @author: Андрей
 */
public interface OrderDAO {

    void insertOrder(Order order);

    List<Order> selectOrdersByUserId(int userId, Pageable pageable);

    int selectCountOrdersByUserId(int userId);
}
