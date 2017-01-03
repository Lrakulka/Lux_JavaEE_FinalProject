package jsf.bean.action;

import ejb.entity.User;
import ejb.facade.UserFacade;
import jsf.bean.model.LoginModel;
import jsf.bean.model.SessionData;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * User: Schuller Tom
 */
@Model
public class LoginAction {
    @Inject
    private Logger logger;
    @Inject
    private UserFacade userFacade;
    @Inject
    private LoginModel credentials;
    @Inject
    private SessionData sessionData;

    public String login() {
        logger.info("login... (user: " + credentials.getUsername() +
                " / " + credentials.getPassword() + ")");
        sessionData.setLoggedUser(null);
        User dbUser = userFacade.findUserByUsername(credentials.getUsername());
        logger.info("dbUser: " + dbUser);
        if (dbUser != null) {
            if (dbUser.getPassword().equals(credentials.getPassword())) {
                //login is ok;
                sessionData.setLoggedUser(dbUser);
                return "/home.xhtml?faces-redirect=true";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Incorrect username or password"));
        return null;
    }

    public String logout() {
        sessionData.setLoggedUser(null);
        return "/login.xhtml?faces-redirect=true";
    }
}
