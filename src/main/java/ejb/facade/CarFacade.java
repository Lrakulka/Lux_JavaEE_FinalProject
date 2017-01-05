package ejb.facade;

import ejb.entity.Car;
import ejb.entity.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 * Created by Olecsandr Borysov on 03.01.17.
 */
@Named
@Stateless
public class CarFacade extends AbstractDBObjectFacade {

    public Car getCar(User owner) {
        try {
            return (Car) em.createQuery("select c from Car c where c.owner = :owner and c.deleted is null")
                    .setParameter("owner", owner).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
}
