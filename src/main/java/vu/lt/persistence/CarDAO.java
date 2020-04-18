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
        em.persist(car);
    }

    public Car findById(Integer id) {
        return em.find(Car.class, id);
    }

    public void remove(Integer id) {
        em.remove(findById(id));
    }

    public List<Car> loadAll() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }
}