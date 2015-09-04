package ua.com.dog.hotel.model.entity.user;

/**
 * @since: 21.06.15
 * @author: Андрей
 */
public enum UserStatus {

    ACTIVE(1), BLOCKED(2), DELETED(3);

    private int statusId;

    UserStatus(int statusId) {
        this.statusId = statusId;
    }

    public static UserStatus valueOf(int statusId){
        for (UserStatus status : UserStatus.values()) {
            if (status.getStatusId() == statusId) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum value for statusId " + statusId);
    }

    public int getStatusId() {
        return statusId;
    }

}
