package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarCategoryDTO;
import lk.ijse.spring.service.CarCategoryService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carCategory")
@CrossOrigin
public class CarCategoryController {

    @Autowired
    private CarCategoryService carCategoryService;


    @PutMapping
    public ResponseUtil updateCarCategory(@RequestBody CarCategoryDTO dto) {
        System.out.println(dto.toString());
        carCategoryService.updateCarCategory(dto);
        return new ResponseUtil("200", dto.getCarCategoryId() + " : Updated", null);
    }

    @PostMapping
    public ResponseUtil saveCarCategory(@ModelAttribute CarCategoryDTO dto) {
        System.out.println(dto.toString());
        carCategoryService.addCarCategory(dto);
        return new ResponseUtil("200", dto.getCarCategoryId() + " : Saved", null);
    }

    /*GET LAST CAR CATEGORY ID*/
    @GetMapping(path = "/carCategoryId")
    public ResponseUtil getLastCarCategoryIdFromDbTable() {

        String lastCarCategory;

        if (carCategoryService.getCarCategoryCount() > 0) {

            lastCarCategory = carCategoryService.getLastDriverId();
        } else {

            lastCarCategory = "CC-000";
        }

        return new ResponseUtil("200", "success", lastCarCategory);
    }

}
