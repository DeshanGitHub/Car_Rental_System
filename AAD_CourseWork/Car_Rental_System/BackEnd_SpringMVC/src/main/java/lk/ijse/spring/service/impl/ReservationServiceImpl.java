package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.ReservationDetailDTO;
import lk.ijse.spring.entity.PaymentDetail;
import lk.ijse.spring.entity.ReservationDetail;
import lk.ijse.spring.repo.PaymentDetailRepo;
import lk.ijse.spring.repo.ReservationDetailRepo;
import lk.ijse.spring.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDetailRepo reservationDetailRepo;

    @Autowired
    private PaymentDetailRepo paymentDetailRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addReservation(ReservationDetailDTO dto) {

        ReservationDetail reservationDetail = mapper.map(dto, ReservationDetail.class);

        if (reservationDetailRepo.existsById(reservationDetail.getBookingId())) {
            throw new RuntimeException("Reservation : " + reservationDetail.getBookingId() + " Already Available.!");
        }

        reservationDetailRepo.save(reservationDetail);


        for (PaymentDetail paymentDetail : reservationDetail.getPaymentDetails()) {

            if (paymentDetailRepo.existsById(paymentDetail.getPaymentId())) {
                throw new RuntimeException("Payment : " + reservationDetail.getBookingId() + " Already Available.!");
            }

            paymentDetailRepo.save(paymentDetail);
        }
    }

    @Override
    public void deleteReservation(String id) {

    }

    @Override
    public void updateReservation(ReservationDetailDTO dto) {

    }

    @Override
    public ArrayList<ReservationDetailDTO> getAllReservations() {
        return null;
    }

    @Override
    public int getReservationCount() {
        return 0;
    }

    @Override
    public String getLastReservationId() {
        return null;
    }

    @Override
    public void isExistsReservation(String id) {

    }

    @Override
    public CarDTO getReservationById(String carId) {
        return null;
    }
}
