package vu.lt.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "company")
    private List<Car> carList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "COMPANY_DRIVER",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private List<Driver> driverList = new ArrayList<>();
}
