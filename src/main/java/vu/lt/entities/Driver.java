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

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="COMPANY_ID")
    private Company company;

    @ManyToMany
    @JoinTable(name = "CAR_DRIVER",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> carList = new ArrayList<>();
}
