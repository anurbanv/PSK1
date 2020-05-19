package vu.lt.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Company;

@Getter
@Setter
public class CompanyDTO {

    public CompanyDTO() {
    }

    public CompanyDTO(Company company) {
        id = company.getId();
        name = company.getName();
    }

    private Integer id;

    private String name;
}
