package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addCar(CarDTO dto) {
        if (carRepo.existsById(dto.getCarId())) {
            throw new RuntimeException("Car " + dto.getCarCategory() + " Already Exists..!!");
        }
        carRepo.save(mapper.map(dto, Car.class));
    }

    @Override
    public void deleteCar(String id) {
        if (!carRepo.existsById(id)) {
            throw new RuntimeException("Car " + id + " Not Available to Delete..!");
        }
        carRepo.deleteById(id);
    }

    @Override
    public void updateCar(CarDTO dto) {
        if (!carRepo.existsById(dto.getCarId())) {
            throw new RuntimeException("Car " + dto.getCarId() + " Not Available to Update..!");
        }
        carRepo.save(mapper.map(dto, Car.class));
    }

    @Override
    public ArrayList<CarDTO> getAllCars() {
        return mapper.map(carRepo.findAll(), new TypeToken<ArrayList<CarDTO>>() {
        }.getType());
    }

    @Override
    public int getCarCount() {
        return carRepo.getCarRowCount();
    }

    @Override
    public String getLastCarId() {
        return carRepo.getCarLastId();
    }

    @Override
    public void isExistsCar(String id) {
        if (carRepo.existsById(id)) {
            throw new RuntimeException("Car " + id + " Already Exists..!!");
        }
    }

    @Override
    public void updateCarWithoutImages(CarDTO dto) {
        Car car = mapper.map(dto, Car.class);

        if (!carRepo.existsById(car.getCarId())) {
            throw new RuntimeException("Car " + car.getCarId() + " Not Available to Update..!");
        }

        carRepo.updateCarWithoutImages(car.isAvailability(), car.getColor(), car.getMileage(), car.getMileageLastServiced(), car.getPassengersCount(), car.getRegistrationNum(), car.getCarCategory().getCarCategoryId(), car.getCarId());

    }

    @Override
    public CarDTO getCarById(String carId) {
        if (!carRepo.existsById(carId)) {
            throw new RuntimeException("No " + carId + " Customer..!!");
        }

        return mapper.map(carRepo.findCarByCarId(carId), CarDTO.class);
    }
}
