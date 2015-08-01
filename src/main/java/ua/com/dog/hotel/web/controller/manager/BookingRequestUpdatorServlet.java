package ua.com.dog.hotel.web.controller.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import ua.com.dog.hotel.dao.DBManager;
import ua.com.dog.hotel.util.Path;

public class BookingRequestUpdatorServlet extends HttpServlet {

	private static final long serialVersionUID = 6029753193631244651L;
	private static final Logger LOG = Logger.getLogger(BookingRequestUpdatorServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		int roomId = Integer.parseInt(request.getParameter("room_id"));
		int bookingRequestId = Integer.parseInt(request.getParameter("bookingRequestId"));
		dbm.updateBookingRequestRoomId(bookingRequestId, roomId);
		request.getSession().setAttribute("info", "User request processed successfull");
		request.getRequestDispatcher(Path.PAGE_SUCCESS_PROCESSING_MANAGER).forward(request, response);
	}
}
