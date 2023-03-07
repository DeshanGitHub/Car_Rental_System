package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class CarCategory {
    @Id
    private String carCategoryId;
    private String carCategoryName;
    private String carModel;
    private String carBrandName;
    private String carType;
    private String carTransmissionType;
    private String carFuelType;
    private BigDecimal dailyRatePrice;
    private BigDecimal monthlyRatePrice;
    private BigDecimal freeKmForDay;
    private BigDecimal freeKmForMonth;
    private BigDecimal pricePerExtraKm;
}
