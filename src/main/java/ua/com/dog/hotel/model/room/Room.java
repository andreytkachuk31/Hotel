package ua.com.dog.hotel.model.room;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

	private static final long serialVersionUID = -5147341111598587109L;

	private int id;
	private int number;
	private int placeAmount;
    private int categoryId;
	private int price;
	private int busyStateId;

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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return RoomCategory.valueOf(categoryId).getName();
	}

	public int getBusyStateId() {
		return busyStateId;
	}

	public void setBusyStateId(int busyState) {
		this.busyStateId = busyState;
	}

	@Override
	public String toString() {
		return "Room {" +
				"id=" + id +
				", number=" + number +
				", placeAmount=" + placeAmount +
				", categoryId=" + categoryId +
				", price=" + price +
				", busyStateId=" + busyStateId +
				"}";
	}

	@Override
	public int hashCode() {
        return Objects.hash(id, number, placeAmount, categoryId, price, busyStateId);
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

		Room other = (Room) obj;

        return Objects.equals(id, other.id)
            && Objects.equals(number, other.number)
            && Objects.equals(placeAmount, other.placeAmount)
            && Objects.equals(categoryId, other.categoryId)
            && Objects.equals(price, other.price)
            && Objects.equals(busyStateId, other.busyStateId);
	}

}
