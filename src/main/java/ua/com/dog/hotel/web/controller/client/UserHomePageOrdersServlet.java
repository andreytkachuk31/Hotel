package ua.com.dog.hotel.web.controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import ua.com.dog.hotel.dao.DBManager;
import ua.com.dog.hotel.model.order.Order;
import ua.com.dog.hotel.model.user.User;
import ua.com.dog.hotel.util.Path;

public class UserHomePageOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = -9159934896957275668L;
    private static final Logger LOG = Logger.getLogger(UserHomePageOrdersServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBManager dbm = DBManager.getInstance();
        User user = (User) request.getSession().getAttribute("user");
        List<Order> userOrders = dbm.selectUserOrders(user.getId());
        request.getSession().setAttribute("userOrders", userOrders);
        request.getRequestDispatcher(Path.PAGE_USER_ORDERS).forward(request, response);
    }
}