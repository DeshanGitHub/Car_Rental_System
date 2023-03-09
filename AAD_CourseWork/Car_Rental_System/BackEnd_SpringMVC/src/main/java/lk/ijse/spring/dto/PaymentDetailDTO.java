package lk.ijse.spring.dto;

import lk.ijse.spring.entity.ReservationDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PaymentDetailDTO {

    private String paymentId;
    private BigDecimal amount;
    private LocalDate date;
    private String reason;
    private String bnkSlipPath;
    private boolean status;
    private String statusReason;

    private String reservationDetail;

}
