package ua.com.dog.hotel.model.user;

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
    private int roleId;
    private int statusId;

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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", login=" + login +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", roleId=" + roleId +
                ", statusId=" + statusId +
                "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, login, password, roleId, statusId);
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
            && Objects.equals(roleId, other.roleId)
            && Objects.equals(statusId, other.statusId);
    }
}
