package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Company;
import vu.lt.persistence.CompanyDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class CompanyDetails {
    @Inject
    private CompanyDAO companyDAO;

    @Getter
    @Setter
    private Company companyToShow;

    @PostConstruct
    private void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer companyId = Integer.parseInt(params.get("companyId"));
        companyToShow = companyDAO.findById(companyId);
    }
}
