package entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by set on 03.01.17.
 */
@Entity
public class Track extends AbstractDBObject {
    private String name;
    private User owner;
    private String startLocation;
    private String stopLocation;
    private Integer maxCompanions;
    private List<User> companions;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(name = "startLocation")
    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    @Column(name = "stopLocation")
    public String getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    @Column(name = "maxCompanions")
    public Integer getMaxCompanions() {
        return maxCompanions;
    }

    public void setMaxCompanions(Integer maxCompanions) {
        this.maxCompanions = maxCompanions;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<User> getCompanions() {
        return companions;
    }

    public void setCompanions(List<User> companions) {
        this.companions = companions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Track track = (Track) o;

        if (!name.equals(track.name)) return false;
        if (!startLocation.equals(track.startLocation)) return false;
        if (!stopLocation.equals(track.stopLocation)) return false;
        return maxCompanions.equals(track.maxCompanions);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + startLocation.hashCode();
        result = 31 * result + stopLocation.hashCode();
        result = 31 * result + maxCompanions.hashCode();
        return result;
    }
}
