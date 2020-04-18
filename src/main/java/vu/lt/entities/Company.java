package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Company.findAll", query = "select a from Company as a"),
})
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Car> carList = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Driver> driverList = new ArrayList<>();
}
