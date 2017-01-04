package ejb.facade;

import ejb.entity.Track;
import ejb.entity.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Olecsandr Borysov on 03.01.17.
 */
@Named
@Stateless
public class TrackFacade extends AbstractDBObjectFacade {

    public List<Track> getAll() {
        try {
            return em.createQuery("select t from Track t where t.deleted is null "
                    + "and t.freePlaces > 0").getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public List<Track> getAll(User loggedUser) {
        try {
            List<Track> tracks = em.createQuery("select t from Track t where t.deleted is null "
                    + "and t.freePlaces > 0").getResultList();
            if (loggedUser != null) {
                for (Track track : loggedUser.getReservedTracks()) {
                    if (!tracks.contains(track)) {
                        tracks.add(track);
                    }
                }
            }
            return tracks;
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public List<Track> getTrackByDestination(String start, String stop){
        try {
            return em.createQuery("select t from Track t where t.startLocation =:start " +
                    "and  t.stopLocation = :stop and t.deleted is null and t.freePlaces > 0")
                    .setParameter("start", start).setParameter("stop", stop).getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public List<Track> getUserTracks(User user) {
        // I use eager fetch
        return user.getTracks();
    }

    public List<Track> getUserReservedTracks(User user) {
        // I use eager fetch
        return user.getReservedTracks();
    }

    public Track getTrackByName(String trackName) {
        try {
            return (Track) em.createQuery("select t from Track t where t.name = :name and t.deleted is null")
                    .setParameter("name", trackName).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public Track getTrackByID(int trackID) {
        try {
            return (Track) em.createQuery("select t from Track t where t.id = :id and t.deleted is null")
                    .setParameter("id", trackID).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
}
