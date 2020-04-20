package vu.lt.usecases;

import lombok.Getter;
import vu.lt.mybatis.dao.CarMapper;
import vu.lt.mybatis.model.Car;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class CarsMyBatis {
    @Inject
    private CarMapper carMapper;

    @Getter
    private List<Car> allCars;

    @PostConstruct
    public void init() {
        allCars = carMapper.selectAll();
    }
}