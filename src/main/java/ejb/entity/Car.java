package ejb.entity;

import javax.persistence.*;

/**
 * Created by Oleksandr Borysov on 03.01.17.
 */
@Entity
public class Car extends AbstractDBObject {
    private String name;
    private User owner;
    private String info;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (!name.equals(car.name)) return false;
        return info != null ? info.equals(car.info) : car.info == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
