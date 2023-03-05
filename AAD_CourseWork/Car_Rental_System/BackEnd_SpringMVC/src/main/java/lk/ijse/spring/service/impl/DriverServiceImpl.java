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

}
