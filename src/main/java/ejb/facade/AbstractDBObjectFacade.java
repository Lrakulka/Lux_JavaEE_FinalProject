package ejb.facade;

import ejb.entity.AbstractDBObject;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

/**
 * User: schullto
 * modified by Oleksandr Borysov
 */
public abstract class AbstractDBObjectFacade implements Serializable {
    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected Logger log;

    public <T> T find(Class<T> clazz, Long id) {
        log.info("Find " + clazz.getName() + " by id=" + id);
        return em.find(clazz, id);
    }

    public void delete(AbstractDBObject dbObject) {
        log.info(dbObject + "Delete");
        dbObject.setDeleted(new Date());
        dbObject.setName(dbObject.getName() + "_d");
        saveOrUpdate(dbObject);
    }

    public AbstractDBObject saveOrUpdate(AbstractDBObject dbObject) {
        log.info("Save or Update " + dbObject);
        return em.merge(dbObject);
    }
}