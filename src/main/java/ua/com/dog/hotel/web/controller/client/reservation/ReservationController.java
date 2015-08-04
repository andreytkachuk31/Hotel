package ua.com.dog.hotel.web.controller.client.reservation;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.dog.hotel.model.order.Order;
import ua.com.dog.hotel.model.room.Room;
import ua.com.dog.hotel.model.user.User;
import ua.com.dog.hotel.model.user.UserPrincipal;
import ua.com.dog.hotel.service.order.OrderService;
import ua.com.dog.hotel.service.room.RoomService;
import ua.com.dog.hotel.web.validator.client.ReservationValidator;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static ua.com.dog.hotel.util.Path.PAGE_RESERVATION;
import static ua.com.dog.hotel.util.Path.PAGE_RESERVATION_LIST;

/**
 * @author Andrii_Tkachuk
 * @since 4/7/2015
 */
@Controller
@RequestMapping("reservation")
public class ReservationController {

    private static final Logger LOG = Logger.getLogger(ReservationController.class);

    @Autowired
    private RoomService roomService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/showList", method = RequestMethod.GET)
    public String showReservationList(final Model model) {
        User user = getCurrentUser();
        List<Order> orders = orderService.selectOrdersByUserId(user.getId());
        model.addAttribute("orders", orders);
        return PAGE_RESERVATION_LIST;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String reservationShowRoom(@RequestParam final int roomId, final Model model) {
        model.addAttribute("roomId", roomId);
        model.addAttribute("status", "SAVE");
        return PAGE_RESERVATION;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String reservationSaveRoom(@RequestParam final int roomId,
                                      @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") final Date dateCheckIn,
                                      @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") final Date dateCheckOut,
                                      final HttpSession session,
                                      final Model model) {
        LOG.trace("Request parameter: roomId --> " + roomId);
        LOG.trace("Request parameter: checkIn --> " + dateCheckIn);
        LOG.trace("Request parameter: checkOut --> " + dateCheckOut);

        String errorMessage = new ReservationValidator().validate(dateCheckIn, dateCheckOut);

        if (StringUtils.isNotBlank(errorMessage)) {
            session.setAttribute("error", errorMessage);
            return "redirect:error";
        } else {
            Room room = roomService.selectRoomById(roomId);
            User user = getCurrentUser();
            int daysBooking = Days.daysBetween(new DateTime(dateCheckIn), new DateTime(dateCheckOut)).getDays();

            Order order = new Order();
            order.setRoomId(roomId);
            order.setUserId(user.getId());
            order.setDateCheckIn(dateCheckIn);
            order.setDateCheckOut(dateCheckOut);
            order.setBill(daysBooking * room.getPrice());

            model.addAttribute("room", room);
            model.addAttribute("daysBooking", daysBooking);
            model.addAttribute("status", "CONFIRM");

            session.setAttribute("order", order);

            return PAGE_RESERVATION;
        }
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String reservationConfirmRoom(final HttpSession session, final Model model) {
        Order order = (Order) session.getAttribute("order");
        Room room = roomService.selectRoomById(order.getRoomId());
        if (room.getBusyStateId() != 1 && room.getBusyStateId() != 2) {
            model.addAttribute("status", "SUCCESS");
            orderService.makeOrder(order);
            return PAGE_RESERVATION;
        } else {
            session.setAttribute("error", " Requested room is not free now");
            return "redirect:error";
        }
    }

    public User getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getUser();
    }
}
