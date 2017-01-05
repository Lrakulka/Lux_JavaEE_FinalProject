package jsf.bean.crud;

import ejb.entity.Car;
import ejb.entity.Track;
import ejb.facade.AbstractDBObjectFacade;
import ejb.facade.CarFacade;
import jsf.bean.model.SessionData;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Olecsandr Borysov on 05.01.17.
 */
@Named
@ConversationScoped
public class CarCrudBean extends AbstractDBObjectCrudBean<Car>  {
    @Inject
    private Conversation conversation;
    @Inject
    private CarFacade facade;
    @Inject
    private SessionData sessionData;

    @Override
    public String doSaveEdit() {
        entity.setOwner(sessionData.getLoggedUser());
        sessionData.getLoggedUser().setCar(entity);
        return super.doSaveEdit();
    }

    @Override
    public String doDelete() {
        sessionData.getLoggedUser().setCar(null);
        return super.doDelete();
    }

    @Override
    public Class getClazz() {
        return Car.class;
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
