package ua.com.dog.hotel.service.bookingrequest;

import ua.com.dog.hotel.model.entity.bookingrequest.BookingRequest;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
public interface BookingRequestService {

    void makeBookingRequest(BookingRequest bookingRequest);

    BookingRequest selectBookingRequestsById(int id);

    List<BookingRequest> selectBookingRequestsByUserId(int userId);

    List<BookingRequest> selectAllBookingRequests();

    void updateBookingRequestStatusById(int id);
}
