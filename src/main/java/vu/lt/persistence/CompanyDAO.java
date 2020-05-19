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

    public void add(Company company){
        em.persist(company);
    }

    public void remove(Integer id) {
        em.remove(getById(id));
    }

    public Company getById(Integer id) {
        return em.find(Company.class, id);
    }

    public void update(Company company) {
        em.merge(company);
    }

    public List<Company> findAll() {
        return em.createNamedQuery("Company.findAll", Company.class).getResultList();
    }
}