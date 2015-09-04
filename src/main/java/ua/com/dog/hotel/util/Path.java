package ua.com.dog.hotel.util;

/**
 * Path holder (jsp pages).
 * 
 * @author Iegor
 * 
 */
public final class Path {

    public static final String PAGE_LOGIN = "/common/authorization/login";
    public static final String PAGE_MLOGIN = "/common/authorization/mlogin";
    public static final String PAGE_REGISTRATION = "/common/authorization/registration";
    public static final String PAGE_ERROR = "/common/error/error";

    public static final String PAGE_HOME_ADMIN = "/admin/home/home";
    public static final String PAGE_USER_LIST_ADMIN = "/admin/users/userList";
    public static final String PAGE_USER_ADMIN = "/admin/users/user";
    public static final String PAGE_USER_ADMIN_SUCCESS = "/admin/users/userSuccess";
    public static final String PAGE_ROOM_LIST_ADMIN = "/admin/rooms/roomList";

    public static final String PAGE_HOME_MANAGER = "/manager/home/home";
    public static final String PAGE_BOOKING_REQUESTS_LIST_MANAGER = "/manager/bookingRequest/bookingRequestsProcessList";
    public static final String PAGE_SUCCESS_PROCESSING_MANAGER = "/manager/bookingRequest/bookingRequestProcessSuccess";
    public static final String PAGE_ROOM_FINDER_MANAGER = "/manager/bookingRequest/findingRoomList";

	public static final String PAGE_HOME = "/client/home/home";
    public static final String PAGE_FREE_ROOMS = "/client/rooms/freeRooms";
	public static final String PAGE_BOOKING_REQUEST = "/client/bookingRequest/bookingRequest";
    public static final String PAGE_BOOKING_REQUEST_LIST = "/client/bookingRequest/bookingRequestList";
    public static final String PAGE_BOOKING_REQUEST_SUCCESS = "/common/inform/success";
	public static final String PAGE_RESERVATION = "/client/reservation/reservation";
    public static final String PAGE_RESERVATION_LIST = "/client/reservation/reservationList";
}