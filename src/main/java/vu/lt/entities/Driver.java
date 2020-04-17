package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Driver.findAll", query = "select a from Driver as a"),
})
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "COMPANY_DRIVER",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Company> companyList = new ArrayList<>();
}
