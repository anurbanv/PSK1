package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.CompanyMapper;
import vu.lt.mybatis.model.Company;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
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
        companyMapper.insert(newCompany);
        return "indexMyBatis?faces-redirect=true";
    }
}