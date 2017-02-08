package ua.com.hotel.dao.bookingrequest;

import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.pagination.Pageable;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
public interface BookingRequestDAO {

    void insertBookingRequest(BookingRequest bookingRequest);

    BookingRequest selectBookingRequestsById(int id);

    List<BookingRequest> selectBookingRequestsByUserId(int userId, Pageable pageable);

    int selectCountBookingRequestsByUserId(int userId);

    List<BookingRequest> selectAllBookingRequests();

    void updateBookingRequestStatusById(int id);
}
