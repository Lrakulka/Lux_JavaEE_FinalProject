package model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by set on 09.12.16.
 */
@Entity
@Table(name = "tracks")
public class Tracks {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "startLocation", nullable = false, length = 45)
    private String startLocation;

    @Column(name = "endLocation", nullable = false, length = 45)
    private String endLocation;

    @Column(name = "places", nullable = false)
    private Integer places;

    @ManyToOne
    @JoinColumn(name = "id_owner", columnDefinition = "INT UNSIGNED", nullable = false)
    private User owner;

    @ManyToOne
    @Column(name = "id_car", columnDefinition = "INT UNSIGNED", nullable = false)
    private Car car;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tracks")
    private Set<User> companions;
}
