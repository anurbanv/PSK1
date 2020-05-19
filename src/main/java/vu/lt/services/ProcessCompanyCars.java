package vu.lt.services;

import vu.lt.entities.Car;
import vu.lt.entities.Company;
import vu.lt.persistence.TransactionEM;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class ProcessCompanyCars implements Serializable {
    @Inject
    @TransactionEM
    EntityManager em;

    @Transactional
    public Integer uploadCarsToCloud(Integer companyId) {
        Company company = em.find(Company.class, companyId);
        List<Car> carList = company.getCarList();

        Integer counter = 0;

        for (Car car : carList) {
            try {
                Thread.sleep(500);
                System.out.println("Uploaded: " + car.getCarNr());
                counter++;
            } catch (InterruptedException e) {
                return counter;
            }
        }
        return counter;
    }
}
