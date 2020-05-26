package vu.lt.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LicencePlateVerifier {

    public boolean verifyNumber(String carNr) {
        return carNr.length() == 7 || carNr.length() == 6;
    }

    public String formatNumber(String carNr) {
        if (carNr.length() == 7) {
            return carNr;
        } else {
            return carNr.substring(0, 3) + " " + carNr.substring(3, 6);
        }
    }
}
