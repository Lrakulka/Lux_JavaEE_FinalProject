package jsf.bean.model;

import ejb.entity.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * User: Schuller Tom
 */
@SessionScoped
@Named
public class SessionData implements Serializable {
    private User loggedUser;

    public boolean isLoggedIn() {
        return loggedUser != null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
