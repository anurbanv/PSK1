package vu.lt.services;

import vu.lt.entities.Car;

public interface IProcessCompanyCars {
    Integer processCars(Integer companyId);
    boolean processCar(Car car);
}
