package ua.com.dog.hotel.util;

/**
 * Path holder (jsp pages).
 * 
 * @author Iegor
 * 
 */
public final class Path {

    public static final String PAGE_LOGIN = "/common/authorization/login";
    public static final String PAGE_REGISTRATION = "/common/authorization/registration";
    public static final String PAGE_SUCCESS = "/common/inform/success";
    public static final String PAGE_ERROR = "/common/inform/error";

	public static final String PAGE_HOME = "/client/home/home";
	public static final String PAGE_BOOKING_REQUEST = "/client/bookingRequest/bookingRequest";
    public static final String PAGE_BOOKING_REQUEST_SHOW_ALL = "/client/bookingRequest/bookingRequestShowAll";
	public static final String PAGE_ROOMS = "/client/rooms/rooms";
	public static final String PAGE_RESERVATION = "/client/reservation/reservation";
	public static final String PAGE_ROOM_FINDER = "/WEB-INF/jsp/manager/roomFinder.jsp";
	public static final String PAGE_USER_ORDERS = "/WEB-INF/jsp/client/home/userOrders.jsp";

	public static final String PAGE_HOME_MANAGER = "/WEB-INF/jsp/manager/home.jsp";
    public static final String PAGE_BOOKING_REQUESTS_MANAGER = "/WEB-INF/jsp/manager/bookingRequestsProcessor.jsp";
    public static final String PAGE_SUCCESS_PROCESSING_MANAGER = "/WEB-INF/jsp/manager/successProcessing.jsp";

    public static final String PAGE_HOME_ADMIN = "/WEB-INF/jsp/admin/home.jsp";
}