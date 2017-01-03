package ejb.facade;

import ejb.entity.Track;
import ejb.entity.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Olecsandr Borysov on 03.01.17.
 */
@Named
@Stateless
public class TrackFacade extends AbstractDBObjectFacade {

    public List<Track> getAll() {
        return em.createQuery("select t from Track t where t.deleted is null "
                + "and t.freePlaces > 0").getResultList();
    }

    public List<Track> getTrackByDestination(String start, String stop){
        return em.createQuery("select t from Track t where (t.startLocation = '"
                + start + "' and t.stopLocation = '"+ stop +"') "
                + "and t.deleted is null and t.freePlaces > 0").getResultList();
    }

    public List<Track> getUserTracks(User user) {
        // I use eager fetch
        return user.getTracks();
    }

    public List<Track> getUserReservedTracks(User user) {
        // I use eager fetch
        return user.getReservedTracks();
    }
}
