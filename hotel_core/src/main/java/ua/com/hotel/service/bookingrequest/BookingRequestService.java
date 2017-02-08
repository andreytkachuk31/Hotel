package ua.com.hotel.service.bookingrequest;

import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.pagination.PaginatedResults;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
public interface BookingRequestService {

    void makeBookingRequest(BookingRequest bookingRequest);

    BookingRequest selectBookingRequestsById(int id);

    PaginatedResults<BookingRequest> selectBookingRequestsByUserId(int userId, Pageable pageable);

    List<BookingRequest> selectAllBookingRequests();

    void updateBookingRequestStatusById(int id);
}
