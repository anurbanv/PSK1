package vu.lt.services;

import vu.lt.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;

@ApplicationScoped
@Default
public class UploadCompanyCars extends ProcessCompanyCarsImpl implements Serializable {

    @Override
    public boolean processCar(Car car) {
        try {
            Thread.sleep(500);
            System.out.println("Uploaded: " + car.getCarNr());
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
