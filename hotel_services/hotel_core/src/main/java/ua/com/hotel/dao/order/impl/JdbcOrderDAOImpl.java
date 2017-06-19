package ua.com.hotel.dao.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.hotel.dao.order.OrderDAO;
import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.rowmapper.OrderRowMapper;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
    public List<Order> selectOrdersByUserId(int userId, Pageable pageable) {
        int limit = pageable.getPerPage();
        int offset = (pageable.getPage() - 1) * limit;
        String sort = pageable.getSort();
        String filter = pageable.getFilter();

        StringBuilder orderBy = new StringBuilder(EMPTY);
        if (isNotBlank(sort)) {
            orderBy.append("ORDER BY " + sort + " ASC");
        }
        StringBuilder whereAnd = new StringBuilder(EMPTY);
        if (isNotBlank(filter)) {
            whereAnd.append("AND rooms.place_amount LIKE '%" + filter + "%'")
                    .append(" OR bill LIKE '%" + filter + "%'");

        }

        return jdbcTemplate.query(
                "SELECT * FROM orders INNER JOIN rooms ON orders.room_id = rooms.id WHERE user_id=? "
                        + whereAnd + " " + orderBy + " LIMIT ? OFFSET ?",
                new Object[]{userId, limit, offset},
                new OrderRowMapper());
    }

    @Override
    public int selectCountOrdersByUserId(int userId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM orders WHERE user_id = ?",
                new Object[]{userId},
                Integer.class);
    }
}
