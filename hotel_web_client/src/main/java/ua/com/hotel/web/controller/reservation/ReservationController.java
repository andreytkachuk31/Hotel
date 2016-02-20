package ua.com.hotel.web.controller.reservation;

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
import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.entity.user.UserPrincipal;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.pagination.PaginatedResults;
import ua.com.hotel.service.order.OrderService;
import ua.com.hotel.service.room.RoomService;
import ua.com.hotel.web.validator.client.ReservationValidator;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Andrii_Tkachuk
 * @since 4/7/2015
 */
@Controller
@RequestMapping("reservation")
public class ReservationController {

    private static final Logger LOG = Logger.getLogger(ReservationController.class);

    private static final String PAGE_RESERVATION = "/client/reservation/reservation";
    private static final String PAGE_RESERVATION_LIST = "/client/reservation/reservationList";

    @Autowired
    private RoomService roomService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/showList", method = RequestMethod.GET)
    public String showReservationList(@RequestParam(defaultValue = "1") final int page,
                                      @RequestParam(defaultValue = "10") final int perPage,
                                      @RequestParam(defaultValue = "bill", required = false) final String sort,
                                      @RequestParam(defaultValue = EMPTY, required = false) final String filter,
                                      final Model model) {

        User user = getCurrentUser();
        Pageable pageable = new Pageable(page, perPage, sort, filter);
        PaginatedResults<Order> paginatedOrders = orderService.selectOrdersByUserId(user.getId(), pageable);

        model.addAttribute("paginatedResults", paginatedOrders);
        model.addAttribute("pageable", pageable);

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
            User user = getCurrentUser();
            Room room = roomService.selectRoomById(roomId);
            int daysBooking = Days.daysBetween(new DateTime(dateCheckIn), new DateTime(dateCheckOut)).getDays();

            Order order = new Order();
            order.setRoom(room);
            order.setUser(user);
            order.setDateCheckIn(dateCheckIn);
            order.setDateCheckOut(dateCheckOut);
            order.setBill(daysBooking * room.getPrice());

            session.setAttribute("order", order);

            model.addAttribute("room", room);
            model.addAttribute("order", order);
            model.addAttribute("daysBooking", daysBooking);
            model.addAttribute("status", "CONFIRM");
        }

        return PAGE_RESERVATION;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String reservationConfirmRoom(final HttpSession session, final Model model) {
        Order order = (Order) session.getAttribute("order");
        orderService.makeOrder(order);

        model.addAttribute("status", "SUCCESS");

        return PAGE_RESERVATION;
    }

    public User getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getUser();
    }
}
