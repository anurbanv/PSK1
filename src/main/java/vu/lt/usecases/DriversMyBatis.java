package vu.lt.usecases;

import lombok.Getter;
import vu.lt.mybatis.dao.DriverMapper;
import vu.lt.mybatis.model.Driver;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class DriversMyBatis {
    @Inject
    private DriverMapper driverMapper;

    @Getter
    private List<Driver> allDrivers;

    @PostConstruct
    public void init() {
        allDrivers = driverMapper.selectAll();
    }
}
