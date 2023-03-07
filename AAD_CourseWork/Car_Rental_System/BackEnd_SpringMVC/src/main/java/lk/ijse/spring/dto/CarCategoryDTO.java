package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CarCategoryDTO {
    private String carCategoryId;
    private String carCategoryName;
    private String carModel;
    private String carBrandName;
    private String carType;
    private String carTransmissionType;
    private String carFuelType;
    private double dailyRatePrice;
    private double monthlyRatePrice;
    private double freeKmForDay;
    private double freeKmForMonth;
    private double pricePerExtraKm;
}
