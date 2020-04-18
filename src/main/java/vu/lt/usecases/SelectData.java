package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import java.io.Serializable;

@Model
@SessionScoped
public class SelectData implements Serializable {
    @Getter
    @Setter
    private Integer carId;

    @Getter
    @Setter
    private Integer driverId;
}