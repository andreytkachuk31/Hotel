package ua.com.dog.hotel.model.room;

/**
 * Status entity.
 */
public enum RoomBusyState {
    FREE(0), RESERVED(1), OCCUPIED(2);

    private final int statusId;

    private RoomBusyState(final int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public static RoomBusyState valueOf(int value) {
        for (RoomBusyState status : RoomBusyState.values()) {
            if (status.getStatusId() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }
}