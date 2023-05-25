package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReservationDetailDTO {
    private String bookingId;
    private boolean status;
    private String destination;
    private boolean driverStatus;
    private String pickupLocation;
    private LocalDate returnDate;
    private String statusReason;
    private LocalDate pickupDate;
    private LocalTime pickupTime;
    private LocalDate requestedDate;
    private BigDecimal distance;

    private String cusID;

    private String driverId;

    private String carId;

    private List<PaymentDetailDTO> paymentDetails;
}
