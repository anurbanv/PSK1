package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Company;
import vu.lt.persistence.CompanyDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Companies {
    @Inject
    private CompanyDAO companyDAO;

    @Getter
    @Setter
    private Company companyToCreate = new Company();

    @Getter
    private List<Company> allCompanies;

    @PostConstruct
    public void init() {
        this.allCompanies = companyDAO.loadAll();
    }

    @Transactional
    public String createCompany() {
        this.companyDAO.persist(companyToCreate);
        return "index?faces-redirect=true";
    }
}
