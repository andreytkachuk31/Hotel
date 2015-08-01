package ua.com.dog.hotel.dao.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ua.com.dog.hotel.dao.order.OrderDAO;
import ua.com.dog.hotel.model.order.Order;

import java.util.List;

/**
 * @since: 10.05.15
 * @author: Андрей
 */
@Repository
public class HibernateOrderDAOImpl implements OrderDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void insertOrder(Order order) {
        hibernateTemplate.persist(order);
    }

    @Override
    public List<Order> selectOrdersByUserId(int userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
