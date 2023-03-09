package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PaymentDetail {
    @Id
    private String paymentId;
    private BigDecimal amount;
    private LocalDate date;
    private String reason;
    private String bnkSlipPath;
    private boolean status;
    private String statusReason;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "bookingId", referencedColumnName = "bookingId", nullable = false)
    private ReservationDetail reservationDetail;
}
