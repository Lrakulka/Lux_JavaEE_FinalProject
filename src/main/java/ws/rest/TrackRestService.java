package ws.rest;

import ejb.entity.Track;
import jsf.bean.action.TrackAction;
import jsf.bean.model.SessionData;
import jsf.bean.model.dto.TrackInfo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olecsandr Borysov on 03.01.17.
 */
@Path("/tracks")
public class TrackRestService {
    @Inject
    private SessionData sessionData;
    @Inject
    private TrackAction trackAction;

    /*
    http://localhost:8080/Lux_JavaEE_FinalProject/rs/tracks/search
     */
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackInfo> getTracks(@QueryParam("start") String start,
                                 @QueryParam("stop") String stop) {
        List<Track> tracks = trackAction.getTrackByDestination(start, stop);
        List<TrackInfo> tracksInfo = new ArrayList<TrackInfo>();
        for (Track track : tracks) {
            tracksInfo.add(new TrackInfo(track));
        }
        return tracksInfo;
    }

    /*
    http://localhost:8080/Lux_JavaEE_FinalProject/rs/tracks/reserv
     */
    @GET
    @Path("/reserv")
    @Produces(MediaType.TEXT_PLAIN)
    public String getReservation(@QueryParam("track") String trackName) {
        if (!sessionData.isLoggedIn()) {
            return "You need to login first";
        }
        if (trackAction.reserv(sessionData.getLoggedUser(), trackName)) {
            return "Reserved";
        } else {
            return "Not reserved";
        }
    }
}