package vu.lt.usecases;

import lombok.Getter;
import vu.lt.entities.Driver;
import vu.lt.persistence.DriverDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Drivers {
    @Inject
    private DriverDAO driverDAO;

    @Getter
    private List<Driver> allDrivers;

    @PostConstruct
    public void init() {
        allDrivers = driverDAO.findAll();
    }
}
