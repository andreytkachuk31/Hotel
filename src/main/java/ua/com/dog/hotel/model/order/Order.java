package ua.com.dog.hotel.model.order;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

	private static final long serialVersionUID = -14773346161997564L;

    private int id;
	private int roomId;
	private int userId;
	private Date dateCheckIn;
    private Date dateCheckOut;
    private Date dateBooking;
    private long bill;

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

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Date getDateBooking() {
		return dateBooking;
	}

	public void setDateBooking(Date dateBooking) {
		this.dateBooking = dateBooking;
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
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", dateBooking=" + dateBooking +
                ", bill=" + bill +
                "}";
	}

	@Override
	public int hashCode() {
        return Objects.hash(id, roomId, userId, dateCheckIn, dateCheckOut, dateBooking, bill);
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

		Order other = (Order) obj;

        return Objects.equals(id, other.id)
            && Objects.equals(roomId, other.roomId)
            && Objects.equals(userId, other.userId)
            && Objects.equals(dateCheckIn, other.dateCheckIn)
            && Objects.equals(dateCheckOut, other.dateCheckOut)
            && Objects.equals(dateBooking, other.dateBooking)
            && Objects.equals(bill, other.bill);
	}
}
