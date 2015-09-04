package ua.com.dog.hotel.dao.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.dog.hotel.dao.order.OrderDAO;
import ua.com.dog.hotel.model.entity.order.Order;
import ua.com.dog.hotel.model.rowmapper.OrderRowMapper;

import java.util.List;

/**
 * @since: 06.02.15
 * @author: Андрей
 */
@Repository
public class JdbcOrderDAOImpl implements OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertOrder(Order order) {
        jdbcTemplate.update(
                "INSERT INTO orders (room_id, user_id, date_check_in, date_check_out, bill) VALUES(?,?,?,?,?)",
                order.getRoom().getId(),
                order.getUser().getId(),
                order.getDateCheckIn(),
                order.getDateCheckOut(),
                order.getBill());
    }

    @Override
    public List<Order> selectOrdersByUserId(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM orders WHERE user_id=?",
                new Object[]{userId},
                new OrderRowMapper());
    }
}
