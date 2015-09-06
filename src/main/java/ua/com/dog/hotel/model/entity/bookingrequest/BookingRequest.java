package ua.com.dog.hotel.model.entity.bookingrequest;

import ua.com.dog.hotel.model.entity.room.Room;
import ua.com.dog.hotel.model.entity.room.RoomCategory;
import ua.com.dog.hotel.model.entity.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookingRequest implements Serializable {

	private static final long serialVersionUID = 4674969354906808505L;

	private int id;
	private User user;
    private Room room;
	private int roomsAmount;
	private Date dateCheckIn;
	private Date dateCheckOut;
	private RoomCategory roomCategory;
	private BookingRequestStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public BookingRequestStatus getStatus() {
        return status;
    }

    public void setStatus(BookingRequestStatus status) {
        this.status = status;
    }

    public String getCategoryName() {
        return roomCategory.getName();
    }

    public String getBookingRequestStatusName() {
        return status.getName();
    }

    @Override
    public String toString() {
        return "BookingRequest {" +
                "id=" + id +
                ", user=" + user +
                ", room=" + room +
                ", roomsAmount=" + roomsAmount +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", roomCategory=" + roomCategory +
                ", status=" + status +
                "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, room, roomsAmount, dateCheckIn, dateCheckOut, roomCategory, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BookingRequest other = (BookingRequest) obj;

        return Objects.equals(id, other.id)
            && Objects.equals(user, other.user)
            && Objects.equals(room, other.room)
            && Objects.equals(roomsAmount, other.roomsAmount)
            && Objects.equals(dateCheckIn, other.dateCheckIn)
            && Objects.equals(dateCheckOut, other.dateCheckOut)
            && Objects.equals(roomCategory, other.roomCategory)
            && Objects.equals(status, other.status);
    }
}
