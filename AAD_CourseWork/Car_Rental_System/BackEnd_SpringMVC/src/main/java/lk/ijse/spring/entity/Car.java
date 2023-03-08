package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Car {
    @Id
    private String carId;
    private String registrationNum;
    private BigDecimal mileage;
    private BigDecimal mileageLastServiced;
    private int passengersCount;
    private boolean availability;
    private String color;
    private String frontImgPath;
    private String backImgPath;
    private String sideImgPath;
    private String interiorPath;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "carCategoryId", referencedColumnName = "carCategoryId", nullable = false)
    private CarCategory carCategory;
}
