package jsf.bean.action;

import ejb.entity.Track;
import ejb.entity.User;
import ejb.facade.TrackFacade;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Olecsandr Borysov on 04.01.17.
 */
@Model
public class TrackAction {
    @Inject
    private Logger logger;
    @Inject
    private TrackFacade trackFacade;


    public List<Track> getTrackByDestination(String start, String stop) {
        return trackFacade.getTrackByDestination(start, stop);
    }

    public boolean isOwner (User loggedUser, Track track) {
        return track.getOwner().equals(loggedUser);
    }

    public boolean isReserved(User loggedUser, Track track) {
        return track.getCompanions().contains(loggedUser);
    }

    public boolean unReserv(User loggedUser, Track track) {
        if (track == null || loggedUser == null
                || track.getOwner().equals(loggedUser)
                || !track.getCompanions().contains(loggedUser)) {
            return false;
        }
        track.setFreePlaces(track.getFreePlaces() + 1);
        List<User> companions = track.getCompanions();
        companions.remove(loggedUser);
        track.setCompanions(companions);
        List<Track> tracks = loggedUser.getReservedTracks();
        tracks.remove(track);
        loggedUser.setReservedTracks(tracks);
        trackFacade.saveOrUpdate(track);
        return true;
    }

    public boolean reserv(User loggedUser, Track track) {
        // Check if reservation is possible
        if (track == null || loggedUser == null
                || track.getOwner().equals(loggedUser)
                || track.getCompanions().contains(loggedUser)
                || track.getFreePlaces() == 0) {
            return false;
        }
        track.getCompanions().add(loggedUser);
        loggedUser.getReservedTracks().add(track);
        track.setFreePlaces(track.getMaxCompanions() - track.getCompanions().size());
        trackFacade.saveOrUpdate(track);
        return true;
    }

    public boolean reserv(User loggedUser, String trackName) {
        Track track = trackFacade.getTrackByName(trackName);
        return reserv(loggedUser, track);
    }
}
