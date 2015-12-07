package ua.com.hotel.web.controller.manager.bookingrequest;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.service.bookingrequest.BookingRequestService;
import ua.com.hotel.service.order.OrderService;
import ua.com.hotel.service.room.RoomService;
import ua.com.hotel.web.util.Path;

import java.util.Date;
import java.util.List;

@RequestMapping("/manager/bookingRequestProcess")
@Controller
public class BookingRequestProcessController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingRequestService bookingRequestService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/showList", method = RequestMethod.GET)
    public String showBookingRequests(final Model model) {
        List<BookingRequest> bookingRequests = bookingRequestService.selectAllBookingRequests();
        model.addAttribute("bookingRequests", bookingRequests);
        return Path.PAGE_BOOKING_REQUESTS_LIST_MANAGER;
    }

    @RequestMapping(value = "/findRooms", method = RequestMethod.GET)
    public String findRooms(@RequestParam final int bookingRequestId, final Model model) {
        List<Room> findingRooms = roomService.selectFreeRoomsByBookingRequestId(bookingRequestId);
        model.addAttribute("findingRooms", findingRooms);
        model.addAttribute("bookingRequestId", bookingRequestId);
        return Path.PAGE_ROOM_FINDER_MANAGER;
    }

    @RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
    public String makeOrder(@RequestParam final int roomId, @RequestParam final int bookingRequestId) {
        BookingRequest bookingRequest = bookingRequestService.selectBookingRequestsById(bookingRequestId);
        Room room = roomService.selectRoomById(roomId);
        Date dateCheckIn = bookingRequest.getDateCheckIn();
        Date dateCheckOut = bookingRequest.getDateCheckOut();
        int daysBooking = Days.daysBetween(new DateTime(dateCheckIn), new DateTime(dateCheckOut)).getDays();

        Order order = new Order();
        order.setRoom(room);
        order.setUser(bookingRequest.getUser());
        order.setDateCheckIn(dateCheckIn);
        order.setDateCheckOut(dateCheckOut);
        order.setBill(daysBooking * room.getPrice());

        orderService.makeOrder(order);
        bookingRequestService.updateBookingRequestStatusById(bookingRequestId);
        roomService.updateRoomBusyStatusBookedById(room.getId());

        return Path.PAGE_SUCCESS_PROCESSING_MANAGER;
    }
}
