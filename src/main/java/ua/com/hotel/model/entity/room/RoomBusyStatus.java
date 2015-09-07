package ua.com.hotel.model.entity.room;

/**
 * Status entity.
 */
public enum RoomBusyStatus {
    FREE(0), BOOKED(1);

    private final int statusId;

    private RoomBusyStatus(final int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public static RoomBusyStatus valueOf(int value) {
        for (RoomBusyStatus status : RoomBusyStatus.values()) {
            if (status.getStatusId() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }
}