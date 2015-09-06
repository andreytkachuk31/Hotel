package ua.com.dog.hotel.model.entity.user;

import java.io.Serializable;

public enum UserRole implements Serializable {
	ROLE_ADMIN(1), ROLE_MANAGER(2), ROLE_CLIENT(3);

	private int roleId;

	private UserRole(int roleId) {
		this.roleId = roleId;
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

}
