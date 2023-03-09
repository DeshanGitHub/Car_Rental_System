package lk.ijse.spring.repo;

import lk.ijse.spring.entity.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailRepo extends JpaRepository<PaymentDetail, String> {
}
