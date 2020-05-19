package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Car;
import vu.lt.entities.Company;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.CarDAO;
import vu.lt.persistence.CompanyDAO;
import vu.lt.services.LicencePlateVerifier;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class ManageCars {

    @Inject
    private CompanyDAO companyDAO;
    @Inject
    private CarDAO carDAO;
    @Inject
    private LicencePlateVerifier plateVerifier;

    @Getter
    @Setter
    private Company company;

    @Getter
    @Setter
    private Car newCar = new Car();

    @PostConstruct
    private void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer companyId = Integer.parseInt(params.get("companyId"));
        company = companyDAO.getById(companyId);
    }

    @Transactional
    @LoggedInvocation
    public String createCar() {
        if (plateVerifier.verifyNumber(newCar.getCarNr())) {
            newCar.setCompany(company);
            carDAO.add(newCar);
            return getReturnUrl();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Incorrect licence plate format use [ABC 234]", null));
            return null;
        }
    }

    @Transactional
    @LoggedInvocation
    public String removeCar(Integer id) {
        carDAO.remove(id);
        return getReturnUrl();
    }

    private String getReturnUrl() {
        return "manageCars?faces-redirect=true&companyId=" + company.getId();
    }
}
