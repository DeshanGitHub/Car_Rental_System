package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    @Autowired
    private CarService carService;

    /*CREATE STATIC VARIABLES TO STORE CAR IMAGE UPLOADING PATH*/
    static String carFrontImgPath;
    static String carBackImgPath;
    static String carSideImgPath;
    static String carInteriorImgPath;

    /*GET CAR BY ID*/
    @GetMapping(path = "/getCar/{carId}")
    public ResponseUtil getCarById(@PathVariable String carId) {
        CarDTO carDTO = carService.getCarById(carId);
        return new ResponseUtil("200", "success", carDTO);
    }

    /*DELETE CAR BY ID*/
    @DeleteMapping(params = "carId")
    public ResponseUtil deleteCar(String carId) {
        CarDTO carDTO = carService.getCarById(carId);
        carService.deleteCar(carId);
        boolean isDeletedImages = deleteUploadedImg(carDTO.getFrontImgPath(), carDTO.getBackImgPath(),carDTO.getSideImgPath(),carDTO.getInteriorPath());
        if (isDeletedImages) {
            return new ResponseUtil("200", carId + " : Deleted", null);
        } else {
            return new ResponseUtil("500", "Car Details Are Deleted, But Images Are Not Deleted!!!", null);
        }

    }

    /*DELETE UPLOADED IMAGE*/
    public boolean deleteUploadedImg(String carFrontImagePath, String carBackImagePath, String carSideImagePath, String carInteriorImagePath) {
        try {

            File fileFront = new File(carFrontImagePath);
            File fileBack = new File(carBackImagePath);
            File fileSide = new File(carSideImagePath);
            File fileInterior = new File(carInteriorImagePath);

            if (fileFront.delete() && fileBack.delete() && fileSide.delete() && fileInterior.delete()) {
                System.out.println("Images are deleted.");
                return true;
            } else {
                System.out.println("Delete operation is failed.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to Delete images !!");
            return false;
        }
    }

    /*GET ALL CARS*/
    @GetMapping
    public ResponseUtil getAllCars() {
        ArrayList<CarDTO> allCars = carService.getAllCars();
        return new ResponseUtil("200", "success", allCars);
    }

    /*UPDATE CAR WITHOUT IMAGES*/
    @PutMapping(path = "/updateWithoutImages")
    public ResponseUtil updateCarWithoutImages(@RequestBody CarDTO dto) {
        carService.updateCarWithoutImages(dto);
        return new ResponseUtil("200", "Updated", null);
    }

    /*UPLOAD IMAGE METHOD*/
    @PostMapping(path = "/carImg/{carId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil uploadCarImages(@RequestPart("carFrontImage") MultipartFile carFrontImg, @RequestPart("carBackImage") MultipartFile carBackImg, @RequestPart("carSideImage") MultipartFile carSideImg, @RequestPart("carInteriorImage") MultipartFile carInteriorImg, @PathVariable String carId) {
        System.out.println("Save Car Image method invoked.");
        try {
            /*FIND CAR IS EXISTS BEFORE SAVING IMAGES*/
            carService.isExistsCar(carId);

            String projectPath = String.valueOf(new File("D:\\GDSE 2022\\All Projects\\AAD_Coursework_Project\\AAD_CourseWork\\Car_Rental_System\\FrontEnd\\assets\\imgUpload"));
            File uploadDir = new File(projectPath + "\\CarImages");
            System.out.println(projectPath);
            uploadDir.mkdir();

            carFrontImgPath = uploadDir.getAbsolutePath() + "\\" + carFrontImg.getOriginalFilename();
            carBackImgPath = uploadDir.getAbsolutePath() + "\\" + carBackImg.getOriginalFilename();
            carSideImgPath = uploadDir.getAbsolutePath() + "\\" + carSideImg.getOriginalFilename();
            carInteriorImgPath = uploadDir.getAbsolutePath() + "\\" + carInteriorImg.getOriginalFilename();

            carFrontImg.transferTo(new File(carFrontImgPath));
            carBackImg.transferTo(new File(carBackImgPath));
            carSideImg.transferTo(new File(carSideImgPath));
            carInteriorImg.transferTo(new File(carInteriorImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseUtil("200", "Images Uploaded", null);
    }


    /*SAVE CAR*/
    @PostMapping
    public ResponseUtil saveCar(@RequestBody CarDTO dto) {
        System.out.println("Save Customer method called");
        dto.setFrontImgPath(carFrontImgPath);
        dto.setBackImgPath(carBackImgPath);
        dto.setSideImgPath(carSideImgPath);
        dto.setInteriorPath(carInteriorImgPath);
        System.out.println("Customer is : " + dto.toString());
        carService.addCar(dto);
        return new ResponseUtil("200", dto.toString() + " : Added", null);
    }

    /*GET LAST CAR ID*/
    @GetMapping(path = "/carId")
    public ResponseUtil getLastCarIdFromDbTable() {

        String lastCarId;

        if (carService.getCarCount() > 0) {

            lastCarId = carService.getLastCarId();
        } else {

            lastCarId = "CAR-000";
        }

        return new ResponseUtil("200", "success", lastCarId);
    }
}
