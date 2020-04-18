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

    public void add(Driver driver){
        em.persist(driver);
    }

    public void remove(Integer id) {
        em.remove(findById(id));
    }

    public Driver findById(Integer id) {
        return em.find(Driver.class, id);
    }

    public List<Driver> findAll() {
        return em.createNamedQuery("Driver.findAll", Driver.class).getResultList();
    }
}
