package ua.com.hotel.model.exception;

/**
 * @author Andrii_Tkachuk
 * @since 12/7/2015
 */
public class RoomNotFoundException extends RoomException {

    public RoomNotFoundException(String message) {
        super(message);
    }
}
