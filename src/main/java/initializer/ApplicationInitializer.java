package initializer;

import model.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Singleton
@Startup
public class ApplicationInitializer {
    @PersistenceContext(unitName="persistence")
        private EntityManager entityManager;

    @PostConstruct
    public void init() {
        //log.info("init....");

        List<User> userList = entityManager.createQuery("select u from User u").getResultList();
        if (userList.size() == 0) {
            //database is empty, creating 'admin' user
            //log.info("creating ADMIN-user...");
            User adminUser = new User();

            //log.info("ADMIN-user created.");
        }
    }
}

