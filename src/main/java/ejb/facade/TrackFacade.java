package ejb.facade;

import ejb.entity.AbstractDBObject;
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

    @Override
    public AbstractDBObject saveOrUpdate(AbstractDBObject dbObject) {
        Track track = (Track) dbObject;
        // Update companions if number of free space was decreased
        if (track != null && track.getCompanions() != null
                && track.getMaxCompanions() - track.getCompanions().size() != track.getFreePlaces()) {
            for (int i = track.getCompanions().size() - 1;
                    track.getMaxCompanions() < track.getCompanions().size(); --i) {
                track.getCompanions().get(i).getReservedTracks().remove(track);
                track.getCompanions().remove(i);
            }
            track.setFreePlaces(track.getMaxCompanions() - track.getCompanions().size());
        }
        return super.saveOrUpdate(dbObject);
    }

    public List<Track> getAllOwner(User owner) {
        try {
            return em.createQuery("select t from Track t where t.deleted is null "
                    + "and t.owner=:owner").setParameter("owner", owner).getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

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

    public Track getTrackByName(String trackName) {
        try {
            return (Track) em.createQuery("select t from Track t where t.name = :name and t.deleted is null")
                    .setParameter("name", trackName).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
}
