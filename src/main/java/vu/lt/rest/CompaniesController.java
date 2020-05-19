package vu.lt.rest;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Company;
import vu.lt.persistence.CompanyDAO;
import vu.lt.rest.contracts.CompanyDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.persistence.PessimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/companies")
public class CompaniesController {

    @Inject
    @Getter
    @Setter
    CompanyDAO companyDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Company company = companyDAO.getById(id);
        if (company == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        CompanyDTO companyDTO = new CompanyDTO(company);
        return Response.ok(companyDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer id, CompanyDTO companyDTO) {
        try {
            Company company = companyDAO.getById(id);
            if (company == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            company.setName(companyDTO.getName());
            companyDAO.update(company);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CompanyDTO companyDTO) {
        try {
            Company company = companyDAO.getById(companyDTO.getId());
            if (company != null) {
                return Response.status(Response.Status.CONFLICT).build();
            }
            Company newCompany = new Company();
            newCompany.setId(companyDTO.getId());
            newCompany.setName(companyDTO.getName());
            companyDAO.add(newCompany);
            return Response.ok().build();
        } catch (OptimisticLockException | PessimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        try {
            List<Company> companies = companyDAO.findAll();
            List<CompanyDTO> companyDTOS = new ArrayList<>();
            companies.forEach(company -> companyDTOS.add(new CompanyDTO(company)));
            return Response.ok(companyDTOS).build();
        } catch (OptimisticLockException | PessimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
