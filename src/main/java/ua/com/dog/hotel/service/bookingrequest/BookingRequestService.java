package ua.com.dog.hotel.service.bookingrequest;

import ua.com.dog.hotel.model.bookingrequest.BookingRequest;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
public interface BookingRequestService {

    void makeBookingRequest(BookingRequest bookingRequest);

    List<BookingRequest> selectBookingRequestsByUserId(int userId);
}
