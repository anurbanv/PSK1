package vu.lt.persistence;

import vu.lt.entities.Driver;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DriverDAO {
    @Inject
    private EntityManager em;

    public void persist(Driver driver){
        this.em.persist(driver);
    }

    public List<Driver> loadAll() {
        return em.createNamedQuery("Driver.findAll", Driver.class).getResultList();
    }
}
