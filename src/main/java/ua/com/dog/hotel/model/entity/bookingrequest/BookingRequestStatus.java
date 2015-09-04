package ua.com.dog.hotel.model.entity.bookingrequest;

public enum BookingRequestStatus {
	PROGRESS(1, "progress"), PROCESSED(2, "processed"), BOOKED(3, "booked"), PAID(4, "paid");

	private final String name;
	private final int value;

	private BookingRequestStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static BookingRequestStatus valueOf(int value) {
		for (BookingRequestStatus requestState : BookingRequestStatus.values()) {
			if (requestState.getValue() == value) {
				return requestState;
			}
		}
		throw new IllegalArgumentException();
	}
}
