package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.ReservationDetailDTO;

import java.util.ArrayList;

public interface ReservationService {
    void addReservation(ReservationDetailDTO dto);

    void deleteReservation(String id);

    void updateReservation(ReservationDetailDTO dto);

    ArrayList<ReservationDetailDTO> getAllReservations();

    int getReservationCount();

    String getLastReservationId();

    void isExistsReservation(String id);

    CarDTO getReservationById(String carId);
}
