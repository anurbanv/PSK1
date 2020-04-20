package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Company;
import vu.lt.persistence.CompanyDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Companies {
    @Inject
    private CompanyDAO companyDAO;

    @Getter
    @Setter
    private Company newCompany = new Company();

    @Getter
    private List<Company> allCompanies;

    @PostConstruct
    public void init() {
        allCompanies = companyDAO.findAll();
    }

    @Transactional
    public String createCompany() {
        try {
            companyDAO.add(newCompany);
        } catch (PersistenceException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Company name already exists", null));
            return null;
        }
        return "index?faces-redirect=true";
    }
}
