package ua.com.hotel.model.exception;

/**
 * @author Andrii_Tkachuk
 * @since 12/7/2015
 */
public class RoomAlreadyBookedException extends RoomException {

    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}
