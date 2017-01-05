package jsf.bean.crud;

import ejb.entity.Track;
import ejb.facade.AbstractDBObjectFacade;

import javax.enterprise.context.Conversation;

/**
 * Created by Olecsandr Borysov on 05.01.17.
 */
public class CarCrudBean extends AbstractDBObjectCrudBean<Track>  {
    public Class getClazz() {
        return null;
    }

    public Conversation getConversation() {
        return null;
    }

    public AbstractDBObjectFacade getFacade() {
        return null;
    }
}
