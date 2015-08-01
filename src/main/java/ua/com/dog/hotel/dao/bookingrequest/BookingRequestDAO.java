package ua.com.dog.hotel.dao.bookingrequest;

import ua.com.dog.hotel.model.bookingrequest.BookingRequest;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
public interface BookingRequestDAO {

    void insertBookingRequest(BookingRequest bookingRequest);

    List<BookingRequest> selectBookingRequestsByUserId(int userId);
}
