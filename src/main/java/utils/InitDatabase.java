package utils;

import ejb.entity.Car;
import ejb.entity.Track;
import ejb.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olecsandr Borysov on 03.01.17.
 */
@Singleton
@Startup
public class InitDatabase {
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        // Get data from database
        List<User> userList = em.createQuery("select u from User u").getResultList();

        if (userList.size() == 0) {
            User adminUser = new User();
            adminUser.setName("admin");
            adminUser.setPassword("admin1");
            adminUser.setAdminRole(true);
            User adminUser1 = new User();
            adminUser1.setName("admin1");
            adminUser1.setPassword("admin1");
            adminUser1.setAdminRole(true);
            User user = new User();
            user.setName("user");
            user.setPassword("user1");
            user.setAdminRole(false);
            User user1 = new User();
            user1.setName("user1");
            user1.setPassword("user1");
            user1.setAdminRole(false);

            Car car = new Car();
            car.setName("CarRepository");
            car.setInfo("This is my car " + car.getName());

            Car car1 = new Car();
            car1.setName("Car1");
            car1.setInfo("This is my car " + car1.getName());
            Car car2 = new Car();
            car2.setName("Car2");
            car2.setInfo("This is my car " + car2.getName());
            Car car3 = new Car();
            car3.setName("Car3");
            car3.setInfo("This is my car " + car3.getName());

            Track track = new Track();
            track.setName("Track");
            track.setMaxCompanions(4);
            track.setFreePlaces(track.getMaxCompanions());
            track.setStartLocation("start");
            track.setStopLocation("stop");
            Track track1 = new Track();
            track1.setName("Track1");
            track1.setMaxCompanions(2);
            track1.setFreePlaces(track1.getMaxCompanions());
            track1.setStartLocation("start");
            track1.setStopLocation("stop");
            Track track2 = new Track();
            track2.setName("Track2");
            track2.setMaxCompanions(3);
            track2.setFreePlaces(track2.getMaxCompanions());
            track2.setStartLocation("start2");
            track2.setStopLocation("stop2");
            Track track3 = new Track();
            track3.setName("Track3");
            track3.setMaxCompanions(2);
            track3.setFreePlaces(track3.getMaxCompanions());
            track3.setStartLocation("start3");
            track3.setStopLocation("stop3");

            adminUser.setCar(car);
            adminUser1.setCar(car1);
            user.setCar(car2);
            user1.setCar(car3);
            car.setOwner(adminUser);
            car1.setOwner(adminUser1);
            car2.setOwner(user);
            car3.setOwner(user1);

            List<Track> ownerTracks = new ArrayList<Track>();
            ownerTracks.add(track);
            adminUser.setTracks(ownerTracks);
            track.setOwner(adminUser);

            ownerTracks = new ArrayList<Track>();
            ownerTracks.add(track1);
            ownerTracks.add(track2);
            adminUser1.setTracks(ownerTracks);
            track1.setOwner(adminUser1);
            track2.setOwner(adminUser1);
            ownerTracks = new ArrayList<Track>();
            ownerTracks.add(track3);
            user.setTracks(ownerTracks);
            track3.setOwner(user);

            /*List<User> companions = new ArrayList<User>();
            companions.add(user1);
            track.setCompanions(companions);
            track1.setCompanions(companions);*/
            /*List<Track> reservedTracks = new ArrayList<Track>();
            reservedTracks.add(track);
            reservedTracks.add(track1);
            user1.setReservedTracks(reservedTracks);*/

            em.merge(adminUser);
            em.merge(adminUser1);
            em.merge(user);
            em.merge(user1);

            List<User> users = em.createQuery("select u from User u").getResultList();
            List<Track> tracks = em.createQuery("select t from Track t").getResultList();
            User u = users.get(3);
            Track t1 = tracks.get(1);
            Track t2 = tracks.get(2);
            List<User> companions = new ArrayList<User>();
            List<Track> reservedTracks = new ArrayList<Track>();
            companions.add(u);
            reservedTracks.add(t1);
            reservedTracks.add(t2);
            u.setReservedTracks(reservedTracks);
            t1.setCompanions(companions);
            t2.setCompanions(companions);
            em.merge(u);
        }
    }
}
