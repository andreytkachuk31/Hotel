package ua.com.dog.hotel.model.entity.order;

import ua.com.dog.hotel.model.entity.room.Room;
import ua.com.dog.hotel.model.entity.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

	private static final long serialVersionUID = -14773346161997564L;

    private int id;
	private Room room;
	private User user;
	private Date dateCheckIn;
    private Date dateCheckOut;
    private long bill;

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

    public long getBill() {
        return bill;
    }

    public void setBill(long bill) {
        this.bill = bill;
    }

	@Override
	public String toString() {
		return "Order {" +
                "id=" + id +
                ", user=" + user +
                ", room=" + room +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", bill=" + bill +
                "}";
	}

	@Override
	public int hashCode() {
        return Objects.hash(id, room, user, dateCheckIn, dateCheckOut, bill);
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

		Order other = (Order) obj;

        return Objects.equals(id, other.id)
            && Objects.equals(room, other.room)
            && Objects.equals(user, other.user)
            && Objects.equals(dateCheckIn, other.dateCheckIn)
            && Objects.equals(dateCheckOut, other.dateCheckOut)
            && Objects.equals(bill, other.bill);
	}
}
