package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {

    @Autowired
    private DriverService driverService;

    /*SAVE DRIVER*/
    @PostMapping
    public ResponseUtil saveDriver(@RequestBody DriverDTO dto) {

        driverService.addDriver(dto);
        return new ResponseUtil("200", dto.getDriverId() + " : Added", "");
    }

    /*GET LAST DRIVER ID*/
    @GetMapping(path = "/driverId")
    public ResponseUtil getLastDriverIdFromDbTable() {
        String lastDriverId;

        if (driverService.getDriversCount() > 0) {

            lastDriverId = driverService.getLastDriverId();
        } else {
            lastDriverId = "D-000";
        }
        return new ResponseUtil("200", "success", lastDriverId);
    }
}
