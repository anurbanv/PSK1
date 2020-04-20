package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.exceptions.PersistenceException;
import vu.lt.mybatis.dao.CompanyMapper;
import vu.lt.mybatis.model.Company;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CompaniesMyBatis {
    @Inject
    private CompanyMapper companyMapper;

    @Getter
    @Setter
    private Company newCompany = new Company();

    @Getter
    private List<Company> allCompanies;

    @PostConstruct
    public void init() {
        allCompanies = companyMapper.selectAll();
    }

    @Transactional
    public String createCompany() {
        try {
            companyMapper.insert(newCompany);
        } catch (PersistenceException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Company name already exists", null));
            return null;
        }
        return "indexMyBatis?faces-redirect=true";
    }
}