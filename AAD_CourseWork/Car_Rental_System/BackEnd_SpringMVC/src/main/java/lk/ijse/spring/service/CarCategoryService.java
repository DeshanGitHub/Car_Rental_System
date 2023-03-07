package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarCategoryDTO;

import java.util.ArrayList;

public interface CarCategoryService {
    void addCarCategory(CarCategoryDTO dto);

    void deleteCarCategory(String id);

    void updateCarCategory(CarCategoryDTO dto);

    ArrayList<CarCategoryDTO> getAllCarCategories();

    int getCarCategoryCount();

    String getLastDriverId();
}
