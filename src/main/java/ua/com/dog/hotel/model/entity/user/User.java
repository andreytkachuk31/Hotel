package ua.com.dog.hotel.model.entity.user;

import java.io.Serializable;
import java.util.Objects;

/**
 * User entity.
 *
 * @author Iegor
 */
public class User implements Serializable {

    private static final long serialVersionUID = -6889036256149495388L;

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
    private UserStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getRoleName() {
        return role.name();
    }

    public String getStatusName() {
        return status.name();
    }

    public boolean isActive() {
        return UserStatus.ACTIVE.equals(status);
    }

    public boolean isBlocked() {
        return UserStatus.BLOCKED.equals(status);
    }

    public boolean isDeleted() {
        return UserStatus.DELETED.equals(status);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", login=" + login +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", roleId=" + role +
                ", statusId=" + status +
                "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, login, password, role, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User other = (User) obj;

        return Objects.equals(id, other.id)
            && Objects.equals(firstName, other.firstName)
            && Objects.equals(lastName, other.lastName)
            && Objects.equals(login, other.login)
            && Objects.equals(password, other.password)
            && Objects.equals(role, other.role)
            && Objects.equals(status, other.status);
    }
}
