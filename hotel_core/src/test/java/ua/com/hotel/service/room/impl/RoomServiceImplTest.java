package ua.com.hotel.service.room.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.hotel.dao.bookingrequest.BookingRequestDAO;
import ua.com.hotel.dao.room.RoomDAO;
import ua.com.hotel.model.entity.bookingrequest.BookingRequest;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.pagination.PaginatedResults;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {

    private static final int ROOM_ID = 1;
    private static final int ROOM_INCORRECT_ID = 1;

    private static final int BOOKING_REQUEST_ID = 1;

    private static final int CURRENT_PAGE = 1;
    private static final String FILTER = "filter";
    private static final String SORT = "sort";


    @Mock
    private BookingRequest bookingRequest;

    @Mock
    private Room room;

    @Mock
    private RoomDAO roomDAO;

    @Mock
    private BookingRequestDAO bookingRequestDAO;

    @Captor
    private ArgumentCaptor<Room> userArgumentCaptor;

    @InjectMocks
    private RoomServiceImpl roomService = new RoomServiceImpl();

    @Test
    public void shouldReturnRoomWhenSelectRoomByIdWithCorrectId() {
        when(roomDAO.selectRoomById(ROOM_ID)).thenReturn(room);

        Room roomActual = roomService.selectRoomById(ROOM_ID);

        assertEquals(room, roomActual);
    }

    @Test
    public void shouldNotReturnRoomWhenSelectRoomByIdWithIncorrectId() {
        when(roomDAO.selectRoomById(ROOM_INCORRECT_ID)).thenThrow(new EmptyResultDataAccessException(1));

        Room roomActual = roomService.selectRoomById(ROOM_INCORRECT_ID);

        assertNull(roomActual);
    }

    @Test
    public void shouldReturnRoomsWhenSelectAllRooms() {
        List<Room> roomsExpected = Arrays.asList(room);
        when(roomDAO.selectAllRooms()).thenReturn(roomsExpected);

        List<Room> roomsActual = roomService.selectAllRooms();

        assertEquals(roomsExpected, roomsActual);
    }

    @Test
    public void shouldReturnRoomsWhenSelectFreeRoomsByBookingRequestId() {
        List<Room> roomsExpected = Arrays.asList(room);
        when(bookingRequestDAO.selectBookingRequestsById(BOOKING_REQUEST_ID)).thenReturn(bookingRequest);
        when(roomDAO.selectFreeRoomsByBookingRequest(bookingRequest)).thenReturn(roomsExpected);

        List<Room> roomsActual = roomService.selectFreeRoomsByBookingRequestId(BOOKING_REQUEST_ID);

        assertEquals(roomsExpected, roomsActual);
    }

    @Test
    public void shouldReturnPaginatedResultsWhenSelectAllFreeRooms1() {
        final int TOTAL_COUNT = 45;
        final int PER_PAGE = 10;
        final int NUMBER_OF_PAGES = 5;

        List<Room> roomsExpected = Arrays.asList(room);
        Pageable pageable = mockRoomsByPageable(TOTAL_COUNT, PER_PAGE, roomsExpected);

        PaginatedResults<Room> roomPaginatedResults = roomService.selectAllFreeRooms(pageable);

        assertEquals(NUMBER_OF_PAGES, roomPaginatedResults.getNumberOfPages());
        assertEquals(TOTAL_COUNT, roomPaginatedResults.getTotalCount());
        assertEquals(roomsExpected, roomPaginatedResults.getResults());
    }

    @Test
    public void shouldReturnPaginatedResultsWhenSelectAllFreeRooms2() {
        final int TOTAL_COUNT = 50;
        final int PER_PAGE = 10;
        final int NUMBER_OF_PAGES = 6;

        List<Room> roomsExpected = Arrays.asList(room);
        Pageable pageable = mockRoomsByPageable(TOTAL_COUNT, PER_PAGE, roomsExpected);

        PaginatedResults<Room> roomPaginatedResults = roomService.selectAllFreeRooms(pageable);

        assertEquals(NUMBER_OF_PAGES, roomPaginatedResults.getNumberOfPages());
        assertEquals(TOTAL_COUNT, roomPaginatedResults.getTotalCount());
        assertEquals(roomsExpected, roomPaginatedResults.getResults());
    }

    @Test
    public void shouldReturnPaginatedResultsWhenSelectAllFreeRooms3() {
        final int TOTAL_COUNT = 0;
        final int PER_PAGE = 10;
        final int NUMBER_OF_PAGES = 1;

        Pageable pageable = mockRoomsByPageable(TOTAL_COUNT, PER_PAGE, Collections.EMPTY_LIST);

        PaginatedResults<Room> roomPaginatedResults = roomService.selectAllFreeRooms(pageable);

        assertEquals(NUMBER_OF_PAGES, roomPaginatedResults.getNumberOfPages());
        assertEquals(TOTAL_COUNT, roomPaginatedResults.getTotalCount());
        assertTrue(roomPaginatedResults.getResults().isEmpty());
    }

    @Test
    public void shouldUpdateRoomStatusWhenUpdateRoomBusyStatusBookedById() {
        roomService.updateRoomBusyStatusBookedById(ROOM_ID);

        verify(roomDAO).updateRoomBusyStatusBookedById(ROOM_ID);
    }

    private Pageable mockRoomsByPageable(int TOTAL_COUNT, int PER_PAGE, List<Room> roomsExpected) {
        Pageable pageable = createPageable(PER_PAGE);

        when(roomDAO.selectAllFreeRooms(pageable)).thenReturn(roomsExpected);
        when(roomDAO.selectCountAllFreeRooms()).thenReturn(TOTAL_COUNT);

        return pageable;
    }

    private Pageable createPageable(int perPage) {
        Pageable pageable = new Pageable();

        pageable.setPerPage(perPage);
        pageable.setPage(CURRENT_PAGE);
        pageable.setSort(SORT);
        pageable.setFilter(FILTER);

        return pageable;
    }
}