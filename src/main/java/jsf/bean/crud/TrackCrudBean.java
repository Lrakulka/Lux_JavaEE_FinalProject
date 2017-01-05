package jsf.bean.crud;

import ejb.entity.Track;
import ejb.entity.User;
import ejb.facade.AbstractDBObjectFacade;
import ejb.facade.TrackFacade;
import jsf.bean.action.TrackAction;
import jsf.bean.model.SessionData;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Iterator;

/**
 * Created by Olecsandr Borysov on 04.01.17.
 */
@Named
@ConversationScoped
public class TrackCrudBean extends AbstractDBObjectCrudBean<Track>  {
    @Inject
    private Conversation conversation;
    @Inject
    private TrackAction trackAction;
    @Inject
    private TrackFacade facade;
    @Inject
    private SessionData sessionData;

    @Override
    public String doSaveEdit() {
        if (entity.getOwner() == null) {
            entity.setOwner(sessionData.getLoggedUser());
            entity.setFreePlaces(entity.getMaxCompanions());
        }
        return super.doSaveEdit();
    }

    // If track delete himself - System make him logout
    @Override
    public String doDelete() {
        Iterator<User> it = entity.getCompanions().iterator();
        while (it.hasNext()) {
            User user = it.next();
            user.getReservedTracks().remove(entity);
        }
        return super.doDelete();
    }

    @Override
    public Class getClazz() {
        return Track.class;
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
