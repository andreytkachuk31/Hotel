package ua.com.dog.hotel.model.user;

import java.io.Serializable;

public enum UserRole implements Serializable {
	ADMIN(1), MANAGER(2), CLIENT(3);

	private int roleId;

	private UserRole(int roleId) {
		this.roleId = roleId;
	}

	public static UserRole getRole(User user) {
		int roleId = user.getRoleId();
		return UserRole.values()[roleId];
	}
	
	public static UserRole valueOf(int value) {
		for (UserRole role : UserRole.values()) {
			if (role.getRoleId() == value) {
				return role;
			}
		}
		throw new IllegalArgumentException();
	}

	public int getRoleId() {
		return roleId;
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}
