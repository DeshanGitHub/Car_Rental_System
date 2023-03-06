package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {

    @Autowired
    private DriverService driverService;

    /*DELETE DRIVER*/
    @DeleteMapping(params = "driverId")
    public ResponseUtil deleteDriver(String driverId) {
        driverService.deleteDriver(driverId);
        return new ResponseUtil("200", driverId + " : Driver is Deleted", null);
    }

    /*UPDATE DRIVER*/
    @PutMapping
    public ResponseUtil updateDriver(@RequestBody DriverDTO driverDTO) {
        driverService.updateDriver(driverDTO);
        return new ResponseUtil("200", driverDTO.getDriverId() + " : Updated", null);
    }

    /*GET DRIVER BY ID*/
    @GetMapping(path = "/getDriver/{driverId}")
    public ResponseUtil getDriverById(@PathVariable String driverId) {
        DriverDTO driver = driverService.getDriverById(driverId);
        return new ResponseUtil("200", "success", driver);
    }

    /*GET ALL DRIVERS*/
    @GetMapping
    public ResponseUtil getAllDrivers() {
        ArrayList<DriverDTO> allDrivers = driverService.getAllDrivers();
        return new ResponseUtil("200", "success", allDrivers);
    }

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
