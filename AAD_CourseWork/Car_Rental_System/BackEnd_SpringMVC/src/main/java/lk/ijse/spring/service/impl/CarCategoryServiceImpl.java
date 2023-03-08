package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarCategoryDTO;
import lk.ijse.spring.entity.CarCategory;
import lk.ijse.spring.repo.CarCategoryRepo;
import lk.ijse.spring.service.CarCategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class CarCategoryServiceImpl implements CarCategoryService {

    @Autowired
    private CarCategoryRepo carCategoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addCarCategory(CarCategoryDTO dto) {
        if (carCategoryRepo.existsById(dto.getCarCategoryId())) {
            throw new RuntimeException("Car Category " + dto.getCarCategoryId() + " Already Exists..!!");
        }
        carCategoryRepo.save(mapper.map(dto, CarCategory.class));
    }

    @Override
    public void deleteCarCategory(String id) {
        if (!carCategoryRepo.existsById(id)) {
            throw new RuntimeException("Car Category " + id + " Not Available to Delete..!");
        }
        carCategoryRepo.deleteById(id);
    }

    @Override
    public void updateCarCategory(CarCategoryDTO dto) {
        if (!carCategoryRepo.existsById(dto.getCarCategoryId())) {
            throw new RuntimeException("Car Category " + dto.getCarCategoryId() + " Not Available to Update..!");
        }

        carCategoryRepo.save(mapper.map(dto, CarCategory.class));
    }

    @Override
    public ArrayList<CarCategoryDTO> getAllCarCategories() {
        return mapper.map(carCategoryRepo.findAll(), new TypeToken<ArrayList<CarCategoryDTO>>() {
        }.getType());
    }

    @Override
    public int getCarCategoryCount() {

        return carCategoryRepo.getCarCategoryRowCount();
    }

    @Override
    public String getLastCarCategoryId() {
        return carCategoryRepo.getCarCategoryLastId();
    }

    @Override
    public CarCategoryDTO getCarCategoryById(String id) {
        return mapper.map(carCategoryRepo.findByCarCategoryId(id), CarCategoryDTO.class);
    }
}
