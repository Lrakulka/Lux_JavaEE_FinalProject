package model;

import javax.persistence.*;

/**
 * Created by set on 09.12.16.
 */
@Entity
@Table(name="user_role")
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "role", nullable = false, length = 11)
    private String role;

    @JoinColumn(name = "user_id", columnDefinition = "INT UNSIGNED", nullable = false)
    @ManyToOne
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (!id.equals(userRole.id)) return false;
        return role.equals(userRole.role);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}