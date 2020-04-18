package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Car;
import vu.lt.persistence.CarDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Cars {
    @Inject
    private CarDAO carDAO;

    @Getter
    @Setter
    private Car newCar = new Car();

    @Getter
    private List<Car> allCars;

    @PostConstruct
    public void init() {
        this.allCars = carDAO.findAll();
    }

    @Transactional
    public String createCar() {
        this.carDAO.add(newCar);
        return "index?faces-redirect=true";
    }
}
