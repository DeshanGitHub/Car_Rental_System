package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDTO;

import java.util.ArrayList;

public interface DriverService {
    void addDriver(DriverDTO dto);

    void deleteDriver(String id);

    void updateDriver(DriverDTO dto);

    ArrayList<DriverDTO> getAllDrivers();

    String getLastDriverId();

    int getDriversCount();

    DriverDTO getDriverById(String driverId);
}
