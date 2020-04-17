package vu.lt.persistence;

import vu.lt.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CarDAO {
    @Inject
    private EntityManager em;

    public void persist(Car car){
        this.em.persist(car);
    }

    public List<Car> loadAll() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }
}