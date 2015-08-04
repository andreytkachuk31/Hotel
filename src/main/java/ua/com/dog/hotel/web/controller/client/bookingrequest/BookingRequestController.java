package ua.com.dog.hotel.web.controller.client.bookingrequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.dog.hotel.model.bookingrequest.BookingRequest;
import ua.com.dog.hotel.model.bookingrequest.BookingRequestStatus;
import ua.com.dog.hotel.model.room.RoomCategory;
import ua.com.dog.hotel.model.user.User;
import ua.com.dog.hotel.model.user.UserPrincipal;
import ua.com.dog.hotel.service.bookingrequest.BookingRequestService;
import ua.com.dog.hotel.web.validator.client.BookingRequestValidator;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static ua.com.dog.hotel.util.Path.PAGE_BOOKING_REQUEST_SUCCESS;
import static ua.com.dog.hotel.util.Path.PAGE_BOOKING_REQUEST;
import static ua.com.dog.hotel.util.Path.PAGE_BOOKING_REQUEST_LIST;

/**
 * @since: 10.05.15
 * @author: Андрей
 */
@Controller
@RequestMapping("bookingRequest")
public class BookingRequestController {

    private static final Logger LOG = Logger.getLogger(BookingRequestController.class);

    @Autowired
    private BookingRequestService bookingRequestService;

    @RequestMapping(value="/showList", method = RequestMethod.GET)
    public String showBookingRequests(final Model model) {
        User user = getCurrentUser();
        List<BookingRequest> bookingRequests = bookingRequestService.selectBookingRequestsByUserId(user.getId());
        model.addAttribute("bookingRequests", bookingRequests);
        return PAGE_BOOKING_REQUEST_LIST;
    }

    @RequestMapping(value="/show", method = RequestMethod.GET)
    public String showBookingRequest(final Model model){
        model.addAttribute("categories", RoomCategory.values());
        return PAGE_BOOKING_REQUEST;
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveBookingRequest(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") final Date dateCheckIn,
                                     @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") final Date dateCheckOut,
                                     @RequestParam final int roomsAmount,
                                     @RequestParam final int roomCategory,
                                     final HttpSession session) {

        LOG.trace("Request parameter: checkIn --> " + dateCheckIn);
        LOG.trace("Request parameter: checkOut --> " + dateCheckOut);
        LOG.trace("Request parameter: roomsAmount --> " + roomsAmount);
        LOG.trace("Request parameter: roomCategory --> " + roomCategory);

        String errorMessage = new BookingRequestValidator().validate(dateCheckIn, dateCheckOut);

        if (StringUtils.isNotBlank(errorMessage)) {
            session.setAttribute("error", errorMessage);
            return "redirect:error";
        } else {
            User user = getCurrentUser();

            BookingRequest bookingRequest = new BookingRequest();
            bookingRequest.setRoomId(0);
            bookingRequest.setUserId(user.getId());
            bookingRequest.setCategoryId(roomCategory);
            bookingRequest.setRoomsAmount(roomsAmount);
            bookingRequest.setDateCheckIn(dateCheckIn);
            bookingRequest.setDateCheckOut(dateCheckOut);
            bookingRequest.setStatusId(BookingRequestStatus.PROGRESS.getValue());

            bookingRequestService.makeBookingRequest(bookingRequest);

            return PAGE_BOOKING_REQUEST_SUCCESS;
        }
    }

    public User getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getUser();
    }
}
