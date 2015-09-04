package ua.com.dog.hotel.model.entity.room;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

	private static final long serialVersionUID = -5147341111598587109L;

	private int id;
	private int number;
	private int placeAmount;
    private RoomCategory roomCategory;
	private int price;
	private RoomBusyStatus busyStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPlaceAmount() {
		return placeAmount;
	}

	public void setPlaceAmount(int placeAmount) {
		this.placeAmount = placeAmount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public RoomCategory getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(RoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}

	public String getCategoryName() {
		return roomCategory.getName();
	}

	public RoomBusyStatus getBusyStatus() {
		return busyStatus;
	}

	public void setBusyStatus(RoomBusyStatus busyStatus) {
		this.busyStatus = busyStatus;
	}

	@Override
	public String toString() {
		return "Room {" +
				"id=" + id +
				", number=" + number +
				", placeAmount=" + placeAmount +
				", categoryId=" + roomCategory.getValue() +
				", price=" + price +
				", busyStateId=" + busyStatus.getStatusId() +
				"}";
	}

	@Override
	public int hashCode() {
        return Objects.hash(id, number, placeAmount, roomCategory.getValue(), price, busyStatus.getStatusId());
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

		Room other = (Room) obj;

        return Objects.equals(id, other.id)
            && Objects.equals(number, other.number)
            && Objects.equals(placeAmount, other.placeAmount)
            && Objects.equals(roomCategory.getValue(), roomCategory.getValue())
            && Objects.equals(price, other.price)
            && Objects.equals(busyStatus.getStatusId(), other.busyStatus.getStatusId());
	}

}
