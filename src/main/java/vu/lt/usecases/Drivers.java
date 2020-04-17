package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Driver;
import vu.lt.persistence.DriverDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Drivers {
    @Inject
    private DriverDAO driverDAO;

    @Getter
    @Setter
    private Driver driverToCreate = new Driver();

    @Getter
    private List<Driver> allDrivers;

    @PostConstruct
    public void init() {
        this.allDrivers = driverDAO.loadAll();
    }

    @Transactional
    public String createDriver() {
        this.driverDAO.persist(driverToCreate);
        return "index?faces-redirect=true";
    }
}
