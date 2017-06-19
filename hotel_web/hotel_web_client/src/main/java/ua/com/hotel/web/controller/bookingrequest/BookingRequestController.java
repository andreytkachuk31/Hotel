package ua.com.hotel.web.controller.bookingrequest;

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
import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.entity.bookingrequest.BookingRequestStatus;
import ua.com.hotel.model.entity.room.RoomCategory;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.entity.user.UserPrincipal;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.pagination.PaginatedResults;
import ua.com.hotel.service.bookingrequest.BookingRequestService;
import ua.com.hotel.web.validator.BookingRequestValidator;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @since: 10.05.15
 * @author: Андрей
 */
@Controller
@RequestMapping("bookingRequest")
public class BookingRequestController {

    private static final Logger LOG = Logger.getLogger(BookingRequestController.class);

    private static final String PAGE_BOOKING_REQUEST = "/client/bookingRequest/bookingRequest";
    private static final String PAGE_BOOKING_REQUEST_LIST = "/client/bookingRequest/bookingRequestList";
    private static final String PAGE_BOOKING_REQUEST_SUCCESS = "/client/bookingRequest/bookingRequestSuccess";

    @Autowired
    private BookingRequestService bookingRequestService;

    @RequestMapping(value="/showList", method = RequestMethod.GET)
    public String showBookingRequests(@RequestParam(defaultValue = "1") final int page,
                                      @RequestParam(defaultValue = "10") final int perPage,
                                      @RequestParam(defaultValue = "status", required = false) final String sort,
                                      @RequestParam(defaultValue = EMPTY, required = false) final String filter,
                                      final Model model) {
        User user = getCurrentUser();
        Pageable pageable = new Pageable(page, perPage, sort, filter);
        PaginatedResults<BookingRequest> paginatedBookingRequests = bookingRequestService.selectBookingRequestsByUserId(user.getId(), pageable);

        model.addAttribute("paginatedResults", paginatedBookingRequests);
        model.addAttribute("pageable", pageable);

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
            bookingRequest.setRoom(null);
            bookingRequest.setUser(user);
            bookingRequest.setRoomCategory(RoomCategory.valueOf(roomCategory));
            bookingRequest.setRoomsAmount(roomsAmount);
            bookingRequest.setDateCheckIn(dateCheckIn);
            bookingRequest.setDateCheckOut(dateCheckOut);
            bookingRequest.setStatus(BookingRequestStatus.PROGRESS);

            bookingRequestService.makeBookingRequest(bookingRequest);

            return PAGE_BOOKING_REQUEST_SUCCESS;
        }
    }

    public User getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getUser();
    }
}
