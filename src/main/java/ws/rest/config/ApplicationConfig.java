package ws.rest.config;

import ws.rest.TrackRestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Olecsandr Borysov on 03.01.17.
 */
@ApplicationPath("rs")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(
                TrackRestService.class));
    }
}