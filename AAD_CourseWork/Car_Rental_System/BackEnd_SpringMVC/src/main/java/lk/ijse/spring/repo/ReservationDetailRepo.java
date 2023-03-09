package lk.ijse.spring.repo;

import lk.ijse.spring.entity.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDetailRepo extends JpaRepository<ReservationDetail, String> {
}
