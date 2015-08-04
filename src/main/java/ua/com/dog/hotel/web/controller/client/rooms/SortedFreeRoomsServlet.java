package ua.com.dog.hotel.web.controller.client.rooms;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import ua.com.dog.hotel.dao.DBManager;
import ua.com.dog.hotel.model.room.Room;
import ua.com.dog.hotel.util.Path;

public class SortedFreeRoomsServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2605959909256715776L;
	
	private static final Logger LOG = Logger.getLogger(SortedFreeRoomsServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		List<Room> sortedFreeRooms = dbm.sortedSelectAllFreeRooms(Integer.parseInt(request.getParameter("sortBy")));
		request.setAttribute("sortedFreeRooms", sortedFreeRooms);
		request.getRequestDispatcher(Path.PAGE_FREE_ROOMS).forward(request, response);
	}

}
