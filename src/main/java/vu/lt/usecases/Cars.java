package vu.lt.usecases;

import lombok.Getter;
import vu.lt.entities.Car;
import vu.lt.persistence.CarDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Cars {
    @Inject
    private CarDAO carDAO;

    @Getter
    private List<Car> allCars;

    @PostConstruct
    public void init() {
        allCars = carDAO.findAll();
    }
}
