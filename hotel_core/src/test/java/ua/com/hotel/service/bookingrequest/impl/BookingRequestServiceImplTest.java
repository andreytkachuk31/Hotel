package ua.com.hotel.service.bookingrequest.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.hotel.dao.bookingrequest.BookingRequestDAO;
import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.pagination.PaginatedResults;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookingRequestServiceImplTest {

    private static final int BOOKING_REQUEST_ID = 1;
    private static final int USER_ID = 1;

    @Mock
    private BookingRequest bookingRequest;

    @Mock
    private BookingRequestDAO bookingRequestDAO;

    @InjectMocks
    private BookingRequestServiceImpl bookingRequestService = new BookingRequestServiceImpl();

    @Test
    public void shouldCreateBookingRequestWhenMakeBookingRequest() {
        bookingRequestService.makeBookingRequest(bookingRequest);

        verify(bookingRequestDAO).insertBookingRequest(bookingRequest);
    }

    @Test
    public void shouldReturnBookingRequestWhenSelectBookingRequestsById() {
        when(bookingRequestDAO.selectBookingRequestsById(BOOKING_REQUEST_ID)).thenReturn(bookingRequest);

        BookingRequest bookingRequestActual = bookingRequestService.selectBookingRequestsById(BOOKING_REQUEST_ID);

        assertEquals(bookingRequest, bookingRequestActual);
    }

    @Test
    public void shouldReturnBookingRequestWhenSelectBookingRequestsByUserId() {
        List<BookingRequest> bookingRequestsExpected = Arrays.asList(bookingRequest);
        when(bookingRequestDAO.selectBookingRequestsByUserId(USER_ID, any(Pageable.class))).thenReturn(bookingRequestsExpected);

        PaginatedResults<BookingRequest> bookingRequestsActual = bookingRequestService.selectBookingRequestsByUserId(USER_ID, any(Pageable.class));

        assertEquals(bookingRequestsExpected, bookingRequestsActual.getResults());
    }

    @Test
    public void shouldReturnBookingRequestsWhenSelectAllBookingRequests() {
        List<BookingRequest> bookingRequestsExpected = Arrays.asList(bookingRequest);
        when(bookingRequestDAO.selectAllBookingRequests()).thenReturn(bookingRequestsExpected);

        List<BookingRequest> bookingRequestsActual = bookingRequestService.selectAllBookingRequests();

        assertEquals(bookingRequestsExpected, bookingRequestsActual);
    }

    @Test
    public void shouldUpdateStatusWhenUpdateBookingRequestStatusById() {
       bookingRequestService.updateBookingRequestStatusById(BOOKING_REQUEST_ID);

        verify(bookingRequestDAO).updateBookingRequestStatusById(BOOKING_REQUEST_ID);
    }
}