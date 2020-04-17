package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select a from Car as a"),
})
@Getter
@Setter
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CAR_NR")
    private String carNr;

    @ManyToOne
    @JoinColumn(name="COMPANY_ID")
    private Company company;
}
