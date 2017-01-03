package ws.rest;

import ejb.entity.Track;
import ejb.entity.User;
import ejb.facade.TrackFacade;
import ejb.facade.UserFacade;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Olecsandr Borysov on 03.01.17.
 */
@Path("/tracks")
public class TrackRestService {
    @Inject
    private TrackFacade trackFacade;


    /*
    http://localhost:8080/Lux_JavaEE_FinalProject/rs/tracks/search
     */
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Track> getTracks(@QueryParam("start") String start,
                                 @QueryParam("stop") String stop) {
        return trackFacade.getTrackByDestination(start, stop);
    }

    /*
    http://localhost:8080/tp4/rs/user/count
     */
    @GET
    @Path("/reserv")
    @Produces(MediaType.TEXT_PLAIN)
    public int getUsersCount() {
        return trackFacade.getAll().size();
    }
}