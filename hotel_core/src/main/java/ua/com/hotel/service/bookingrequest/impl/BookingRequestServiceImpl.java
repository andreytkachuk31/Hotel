package ua.com.hotel.service.bookingrequest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.com.hotel.dao.bookingrequest.BookingRequestDAO;
import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.pagination.PaginatedResults;
import ua.com.hotel.service.bookingrequest.BookingRequestService;

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
    public BookingRequest selectBookingRequestsById(int id) {
        return bookingRequestDAO.selectBookingRequestsById(id);
    }

    @Override
    public PaginatedResults<BookingRequest> selectBookingRequestsByUserId(int userId, Pageable pageable) {
        List<BookingRequest> bookingRequests = bookingRequestDAO.selectBookingRequestsByUserId(userId, pageable);
        int totalCount = bookingRequestDAO.selectCountBookingRequestsByUserId(userId);
        int numberOfPages = (totalCount / pageable.getPerPage()) + 1;
        return new PaginatedResults<BookingRequest>(numberOfPages, totalCount, bookingRequests);
    }

    @Override
    public List<BookingRequest> selectAllBookingRequests() {
        return bookingRequestDAO.selectAllBookingRequests();
    }

    @Override
    public void updateBookingRequestStatusById(int id) {
        bookingRequestDAO.updateBookingRequestStatusById(id);
    }
}
