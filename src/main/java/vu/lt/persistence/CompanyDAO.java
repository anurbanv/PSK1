package vu.lt.persistence;

import vu.lt.entities.Company;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CompanyDAO {
    @Inject
    private EntityManager em;

    public void persist(Company company){
        this.em.persist(company);
    }

    public Company findById(Integer id) {
        return em.find(Company.class, id);
    }

    public List<Company> loadAll() {
        return em.createNamedQuery("Company.findAll", Company.class).getResultList();
    }
}