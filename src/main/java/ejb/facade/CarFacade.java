package ejb.facade;

import ejb.entity.Car;
import ejb.entity.User;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by Olecsandr Borysov on 03.01.17.
 */
@Named
@Stateless
public class CarFacade extends AbstractDBObjectFacade {

    public Car getCar(User user) {
        // I use eager fetch
        return user.getCar();
    }
}
