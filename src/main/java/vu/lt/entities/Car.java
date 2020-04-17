package vu.lt.entities;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    private Integer id;

    private String carNR;

    @ManyToOne
    @JoinColumn(name="COMPANY_ID")
    private Company company;
}
