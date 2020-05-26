package vu.lt.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class BetterLicencePlateVerifier extends LicencePlateVerifier {

    @Override
    public boolean verifyNumber(String carNr) {
        System.out.println("VERIFY NUMBER");
        return carNr.matches("^[A-Z]{3} ?[0-9]{3}$");
    }
}
