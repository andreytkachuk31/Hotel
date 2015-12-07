package ua.com.hotel.service.order.impl;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.hotel.dao.order.OrderDAO;
import ua.com.hotel.model.entity.order.Order;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.exception.RoomAlreadyBookedException;
import ua.com.hotel.model.exception.RoomNotFoundException;
import ua.com.hotel.service.room.RoomService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ua.com.hotel.model.entity.room.RoomBusyStatus.BOOKED;
import static ua.com.hotel.model.entity.room.RoomBusyStatus.FREE;
import static ua.com.hotel.model.entity.room.RoomCategory.CAT_A;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    public static final int ORDER_ID = 1;
    private static final long ORDER_BILL = 340;
    private static final Date ORDER_DATE_CHECK_IN = new DateTime(2015, 5, 11, 0, 0).toDate();
    private static final Date ORDER_DATE_CHECK_OUT = new DateTime(2015, 5, 12, 0, 0).toDate();

    public static final int USER_ID = 1;

    public static final int ROOM_ID = 1;
    private static final int ROOM_ID_INCORRECT = -1;
    private static final int ROOM_NUMBER = 510;
    private static final int ROOM_PLACE_AMOUNT = 2;
    private static final int ROOM_PRICE = 10;

    private Order order;

    @Mock
    private RoomService roomService;

    @Mock
    private OrderDAO orderDAO;

    @InjectMocks
    private OrderServiceImpl orderService = new OrderServiceImpl();

    @Test(expected = RoomNotFoundException.class)
    public void shouldThrowRoomNotFoundExceptionWhenMakeOrderWithRoomNull() {
        order = createOrder(null);

        orderService.makeOrder(order);
    }

    @Test(expected = RoomNotFoundException.class)
    public void shouldThrowRoomNotFoundExceptionWhenMakeOrderWithNotExistRoom() {
        Room room = createRoom();
        room.setId(ROOM_ID_INCORRECT);
        when(roomService.selectRoomById(ROOM_ID_INCORRECT)).thenReturn(null);
        order = createOrder(room);

        orderService.makeOrder(order);
    }

    @Test(expected = RoomAlreadyBookedException.class)
    public void shouldThrowRoomAlreadyBookedExceptionWhenMakeOrderWithAlreadyBookedRoom() {
        Room room = createRoom();
        room.setBusyStatus(BOOKED);
        when(roomService.selectRoomById(ROOM_ID)).thenReturn(room);
        order = createOrder(room);

        orderService.makeOrder(order);
    }

    @Test
    public void shouldCreateOrderWhenMakeOrderWithCorrectRoom() {
        Room room = createRoom();
        when(roomService.selectRoomById(ROOM_ID)).thenReturn(room);
        order = createOrder(room);

        orderService.makeOrder(order);

        verify(roomService).updateRoomBusyStatusBookedById(ROOM_ID);
        verify(orderDAO).insertOrder(order);
    }

    @Test
    public void shouldReturnOrdersWhenSelectOrdersByUserId() {
        List<Order> ordersExpected = Arrays.asList(order);
        when(orderDAO.selectOrdersByUserId(USER_ID)).thenReturn(ordersExpected);

        List<Order> ordersActual = orderService.selectOrdersByUserId(USER_ID);

        assertEquals(ordersExpected, ordersActual);

    }

    private Order createOrder(Room room) {
        Order order = new Order();

        order.setId(ORDER_ID);
        order.setRoom(room);
        order.setUser(createUser());
        order.setBill(ORDER_BILL);
        order.setDateCheckIn(ORDER_DATE_CHECK_IN);
        order.setDateCheckOut(ORDER_DATE_CHECK_OUT);

        return order;
    }

    private User createUser() {
        User user = new User();

        user.setId(USER_ID);

        return user;
    }

    private Room createRoom() {
        Room room = new Room();

        room.setId(ROOM_ID);
        room.setNumber(ROOM_NUMBER);
        room.setPlaceAmount(ROOM_PLACE_AMOUNT);
        room.setPrice(ROOM_PRICE);
        room.setBusyStatus(FREE);
        room.setRoomCategory(CAT_A);

        return room;
    }
}