package ua.com.dog.hotel.service.bookingrequest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.com.dog.hotel.dao.bookingrequest.BookingRequestDAO;
import ua.com.dog.hotel.model.bookingrequest.BookingRequest;
import ua.com.dog.hotel.service.bookingrequest.BookingRequestService;

import java.util.List;

/**
 * @since: 07.02.15
 * @author: Андрей
 */
@Service
public class BookingRequestServiceImpl implements BookingRequestService {

    @Autowired
    @Qualifier("jdbcBookingRequestDAOImpl")
    private BookingRequestDAO bookingRequestDAO;

    @Override
    public void makeBookingRequest(BookingRequest bookingRequest) {
        bookingRequestDAO.insertBookingRequest(bookingRequest);
    }

    @Override
    public List<BookingRequest> selectBookingRequestsByUserId(int userId) {
        return bookingRequestDAO.selectBookingRequestsByUserId(userId);
    }
}
