package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Car;
import vu.lt.entities.Company;
import vu.lt.entities.Driver;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.CarDAO;
import vu.lt.persistence.CompanyDAO;
import vu.lt.persistence.DriverDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class CompanyDetails {

    @Inject
    private CompanyDAO companyDAO;
    @Inject
    private CarDAO carDAO;
    @Inject
    private DriverDAO driverDAO;

    @Getter
    @Setter
    private Company company;

    @Getter
    @Setter
    private Car newCar = new Car();

    @Getter
    @Setter
    private Driver newDriver = new Driver();

    @PostConstruct
    private void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer companyId = Integer.parseInt(params.get("companyId"));
        company = companyDAO.findById(companyId);
    }

    @Transactional
    @LoggedInvocation
    public String createCar() {
        newCar.setCompany(company);
        carDAO.add(newCar);
        return "companyDetails?faces-redirect=true&companyId=" + this.company.getId();
    }

    @Transactional
    @LoggedInvocation
    public String removeCar(Integer id) {
        carDAO.remove(id);
        return "companyDetails?faces-redirect=true&companyId=" + this.company.getId();
    }

    @Transactional
    @LoggedInvocation
    public String removeDriver(Integer id) {
        driverDAO.remove(id);
        return "companyDetails?faces-redirect=true&companyId=" + this.company.getId();
    }

    @Transactional
    @LoggedInvocation
    public String createDriver() {
        newDriver.setCompany(company);
        driverDAO.add(newDriver);
        return "companyDetails?faces-redirect=true&companyId=" + this.company.getId();
    }
}
