package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addDriver(DriverDTO dto) {
        if (driverRepo.existsById(dto.getDriverId())) {
            throw new RuntimeException("Driver " + dto.getDriverId() + " Already Exists..!!");
        }
        if (driverRepo.existsDriverByDriverUserName(dto.getDriverUserName())) {
            throw new RuntimeException("Username Already Exists..!! Please Try Another One");
        }

        driverRepo.save(mapper.map(dto, Driver.class));
    }

    @Override
    public void deleteDriver(String id) {
        if (!(driverRepo.existsById(id))) {
            throw new RuntimeException("Driver " + id + " Not Available to Delete..!");
        }

        driverRepo.deleteById(id);
    }

    @Override
    public void updateDriver(DriverDTO dto) {
        if (!(driverRepo.existsById(dto.getDriverId()))) {
            throw new RuntimeException("Driver " + dto.getDriverId() + " Not Available to Update..!");
        }
        if (driverRepo.existsDriverByDriverUserName(dto.getDriverUserName())) {
            throw new RuntimeException("Username Already Exists..!! Please Try Another One");
        }

        driverRepo.save(mapper.map(dto, Driver.class));
    }

    @Override
    public ArrayList<DriverDTO> getAllDrivers() {
        return mapper.map(driverRepo.findAll(), new TypeToken<ArrayList<DriverDTO>>() {
        }.getType());
    }

    @Override
    public String getLastDriverId() {
        return driverRepo.getLastId();
    }

    @Override
    public int getDriversCount() {
        return driverRepo.getRowCount();
    }

    @Override
    public DriverDTO getDriverById(String driverId) {
        return mapper.map(driverRepo.findDriverByDriverId(driverId), DriverDTO.class);
    }

    @Override
    public DriverDTO getDriverByUserNameAndPassword(String userName, String password) {
        Driver driver = driverRepo.findDriverByDriverUserNameAndDriverPassword(userName, password);

        if (driver == null) {
            throw new RuntimeException("Incorrect Password and Username!!");
        }
        return mapper.map(driver, DriverDTO.class);
    }

}
