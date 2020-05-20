package vu.lt.services;

import vu.lt.entities.Car;
import vu.lt.entities.Company;
import vu.lt.persistence.TransactionEM;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public abstract class ProcessCompanyCarsImpl implements IProcessCompanyCars, Serializable {

    @Inject
    @TransactionEM
    EntityManager em;

    @Transactional
    public Integer processCars(Integer companyId) {
        Company company = em.find(Company.class, companyId);
        List<Car> carList = company.getCarList();

        Integer counter = 0;

        for (Car car : carList) {
            boolean success = processCar(car);
            if (success) {
                counter++;
            }
        }
        return counter;
    }

    abstract public boolean processCar(Car car);
}
