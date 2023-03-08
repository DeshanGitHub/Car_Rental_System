package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CarDTO {
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
    private CarCategoryDTO carCategory;
    //private CarCategoryDTO carCategory;
}
