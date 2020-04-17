package vu.lt.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver {

    @Id
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "COMPANY_DRIVER",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Company> companyList = new ArrayList<>();
}
