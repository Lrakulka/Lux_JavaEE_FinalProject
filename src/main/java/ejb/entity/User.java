package ejb.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: Schuller Tom
 * Modified by Oleksandr Borysov
 */
@Entity
public class User extends AbstractDBObject {
    private String password;
    private boolean adminRole;
    private List<Car> cars;
    private List<Track> tracks;
    private List<Track> reservedTracks;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "companions", fetch=FetchType.EAGER)
    public List<Track> getReservedTracks() {
        return reservedTracks;
    }

    public void setReservedTracks(List<Track> reservedTracks) {
        this.reservedTracks = reservedTracks;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "adminRole")
    public boolean isAdminRole() {
        return adminRole;
    }

    public void setAdminRole(boolean adminRole) {
        this.adminRole = adminRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (adminRole != user.adminRole) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (adminRole ? 1 : 0);
        return result;
    }
}
