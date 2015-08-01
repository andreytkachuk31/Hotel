package ua.com.dog.hotel.web.controller.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import ua.com.dog.hotel.dao.DBManager;
import ua.com.dog.hotel.model.bookingrequest.BookingRequest;
import ua.com.dog.hotel.util.Path;

public class BookingRequestProcessServlet extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(BookingRequestProcessServlet.class);

	private static final long serialVersionUID = -7021824678947309871L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		List<BookingRequest> bookingRequests = dbm.selectAllbookingRequests();
		request.setAttribute("bookingRequests", bookingRequests);
		request.getRequestDispatcher(Path.PAGE_BOOKING_REQUESTS_MANAGER).forward(request, response);
	}
}
