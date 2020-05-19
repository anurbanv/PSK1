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

    @Column(name = "NAME", unique = true)
    private String name;

    @Version
    @Column(name = "optimistic_lock_ver")
    private Integer version;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Car> carList = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Driver> driverList = new ArrayList<>();
}
