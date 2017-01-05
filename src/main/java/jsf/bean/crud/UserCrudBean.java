package jsf.bean.crud;

import ejb.entity.User;
import ejb.facade.AbstractDBObjectFacade;
import ejb.facade.UserFacade;
import jsf.bean.model.SessionData;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Schuller Tom
 * Modified by Oleksandr Borysov
 * I added logout of user if he delete himself
 */
@Named
@ConversationScoped
public class UserCrudBean extends AbstractDBObjectCrudBean<User> {
    @Inject
    private Conversation conversation;
    @Inject
    private UserFacade facade;
    @Inject
    private SessionData sessionData;

    // If user delete himself - System make him logout
    @Override
    public String doDelete() {
        if (entity.equals(sessionData.getLoggedUser())) {
            sessionData.setLoggedUser(null);
        }
        return super.doDelete();
    }

    @Override
    public Class getClazz() {
        return User.class;
    }

    @Override
    public Conversation getConversation() {
        return conversation;
    }

    @Override
    public AbstractDBObjectFacade getFacade() {
        return facade;
    }
}