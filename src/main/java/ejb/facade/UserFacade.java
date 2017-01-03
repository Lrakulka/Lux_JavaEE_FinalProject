package ejb.facade;

import ejb.entity.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Schuller Tom
 */
@Named
@Stateless
public class UserFacade extends AbstractDBObjectFacade {
    public List<User> getAll() {
        return em.createQuery("select u from User u where u.deleted is null").getResultList();
    }

    public User findUserByUsername(String username) {
        try {
            return (User) em.createQuery("select u from User u where u.username = :username and u.deleted is null")
                            .setParameter("username", username).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
}