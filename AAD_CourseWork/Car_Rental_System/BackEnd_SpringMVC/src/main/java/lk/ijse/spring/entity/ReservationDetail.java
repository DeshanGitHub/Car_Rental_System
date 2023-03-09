package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ReservationDetail {

    @Id
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

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customerID", referencedColumnName = "cusId", nullable = false)
    private Customer cusID;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "driverId", referencedColumnName = "driverId", nullable = false)
    private Driver driverId;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "carId", referencedColumnName = "carId", nullable = false)
    private Car carId;

    @OneToMany(mappedBy = "reservationDetail", cascade = CascadeType.ALL)
    private List<PaymentDetail> paymentDetails;
}
