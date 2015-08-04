package ua.com.dog.hotel.web.controller.manager;

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

public class RoomFinderServlet extends HttpServlet {

    private static final long serialVersionUID = -1217164557095669410L;

    private static final Logger LOG = Logger.getLogger(RoomFinderServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomsAmount = request.getParameter("roomsAmount");
        String category = request.getParameter("categoryId");
        String bookingRequestId = request.getParameter("bookingRequestId");
        DBManager dbm = DBManager.getInstance();
        List<Room> suitableListRooms = dbm.selectRoomByUserRequest(Integer.parseInt(category), Integer.parseInt(roomsAmount));
        request.setAttribute("suitableListRooms", suitableListRooms);
        request.setAttribute("bookingRequestId", bookingRequestId);
        request.getRequestDispatcher(Path.PAGE_ROOM_FINDER_MANAGER).forward(request, response);

    }
}
