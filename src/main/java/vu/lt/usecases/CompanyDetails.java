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
import vu.lt.rest.contracts.CompanyDTO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;
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

    @PostConstruct
    private void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer companyId = Integer.parseInt(params.get("companyId"));
        company = companyDAO.getById(companyId);
    }

    @Transactional
    @LoggedInvocation
    public String assignCarToDriver(Integer carId, Integer driverId) {
        Car car = carDAO.findById(carId);
        Driver driver = driverDAO.findById(driverId);
        List<Car> carList = driver.getCarList();
        boolean exists = false;
        for (Car car1 : carList) {
            if (car1.getId().equals(car.getId())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            driver.getCarList().add(car);
        }
        return getReturnUrl();
    }

    @Transactional
    @LoggedInvocation
    public String removeCarFromDriver(Integer carId, Integer driverId) {
        Driver driver = driverDAO.findById(driverId);
        List<Car> carList = driver.getCarList();

        for (Car car1 : carList) {
            if (car1.getId().equals(carId)) {
                carList.remove(car1);
                break;
            }
        }
        return getReturnUrl();
    }

    @Transactional
    public String deleteCompany() {
        companyDAO.remove(company.getId());
        return "index?faces-redirect=true";
    }

    public String updateCompany() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/psk1/api/companies/" + company.getId());
        CompanyDTO companyDTO = new CompanyDTO(company);
        target.request(MediaType.APPLICATION_JSON).put(Entity.entity(companyDTO, MediaType.APPLICATION_JSON), CompanyDTO.class);
        return getReturnUrl();
    }

    private String getReturnUrl() {
        return "companyDetails?faces-redirect=true&companyId=" + company.getId();
    }
}
