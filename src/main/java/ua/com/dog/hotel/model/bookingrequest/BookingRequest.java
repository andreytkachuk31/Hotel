package ua.com.dog.hotel.model.bookingrequest;

import ua.com.dog.hotel.model.room.RoomCategory;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookingRequest implements Serializable {

	private static final long serialVersionUID = 4674969354906808505L;

	private int id;
	private int userId;
    private int roomId;
	private int roomsAmount;
	private Date dateCheckIn;
	private Date dateCheckOut;
	private int categoryId;
	private int statusId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(int roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getCategoryName() {
        return RoomCategory.valueOf(categoryId).getName();
    }

    public String getBookingRequestStatusName() {
        return BookingRequestStatus.valueOf(statusId).getName();
    }

    @Override
    public String toString() {
        return "BookingRequest {" +
                "id=" + id +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", roomsAmount=" + roomsAmount +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", categoryId=" + categoryId +
                ", statusId=" + statusId +
                "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, roomId, roomsAmount, dateCheckIn, dateCheckOut, categoryId, statusId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BookingRequest other = (BookingRequest) obj;

        return Objects.equals(id, other.id)
            && Objects.equals(userId, other.userId)
            && Objects.equals(roomId, other.roomId)
            && Objects.equals(roomsAmount, other.roomsAmount)
            && Objects.equals(dateCheckIn, other.dateCheckIn)
            && Objects.equals(dateCheckOut, other.dateCheckOut)
            && Objects.equals(categoryId, other.categoryId)
            && Objects.equals(statusId, other.statusId);
    }
}
