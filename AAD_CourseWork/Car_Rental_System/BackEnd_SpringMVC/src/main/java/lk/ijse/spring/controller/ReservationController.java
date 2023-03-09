package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ReservationDetailDTO;
import lk.ijse.spring.service.ReservationService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseUtil saveReservation(@RequestBody ReservationDetailDTO dto) {
        reservationService.addReservation(dto);
        return new ResponseUtil("200", "Successfully Reserved.!", null);
    }
}
