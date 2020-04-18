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

    public void add(Car car){
        em.persist(car);
    }

    public void remove(Integer id) {
        em.remove(findById(id));
    }

    public Car findById(Integer id) {
        return em.find(Car.class, id);
    }

    public List<Car> findAll() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }
}