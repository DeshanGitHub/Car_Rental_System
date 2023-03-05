package lk.ijse.spring.dto;

import lk.ijse.spring.embeded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DriverDTO {
    private String driverId;
    private NameDTO driverName;
    private String driverAddress;
    private String driverEmail;
    private String driverContactNum;
    private String driverNic;
    private String diverDrivingLicense;
    private String driverUserName;
    private String driverPassword;
}
