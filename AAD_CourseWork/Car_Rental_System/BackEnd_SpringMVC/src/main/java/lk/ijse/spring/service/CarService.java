package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;

import java.util.ArrayList;

public interface CarService {
    void addCar(CarDTO dto);

    void deleteCar(String id);

    void updateCar(CarDTO dto);

    ArrayList<CarDTO> getAllCars();

    int getCarCount();

    String getLastCarId();

    void isExistsCar(String id);

    void updateCarWithoutImages(CarDTO dto);
}
