package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    /*CREATE STATIC VARIABLES TO STORE IMAGE UPLOADING PATH*/
    static String frontImageUploadingPath;
    static String backImageUploadPath;

    /*GET LAST CUSTOMER ID*/
    @GetMapping(path = "/cusId")
    public ResponseUtil getLastCustomerId() {
        String lastCustomerId = "CUS-076";
        return new ResponseUtil("200", "success", lastCustomerId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil uploadImages(@RequestPart("frontImage") MultipartFile frontImage, @RequestPart("backImage") MultipartFile backImage) {
        System.out.println("Save Image method invoked.");
        try {
            String projectPath = String.valueOf(new File("D:\\GDSE 2022\\All Projects\\AAD_Coursework_Project\\AAD_CourseWork\\Car_Rental_System\\FrontEnd\\assets\\imgUpload"));
            File uploadDir = new File(projectPath + "\\CustomerImages");
            System.out.println(projectPath);
            uploadDir.mkdir();

            frontImageUploadingPath = uploadDir.getAbsolutePath() + "\\" + frontImage.getOriginalFilename();
            backImageUploadPath = uploadDir.getAbsolutePath() + "\\" + backImage.getOriginalFilename();

            frontImage.transferTo(new File(frontImageUploadingPath));
            backImage.transferTo(new File(backImageUploadPath));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseUtil("200", "Images Uploaded", null);
    }

    @PostMapping
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO dto) {
        System.out.println("Save Customer method called");
        dto.setCusFrontImgPath(frontImageUploadingPath);
        dto.setCusBackImgPath(backImageUploadPath);
        System.out.println("Customer is : " + dto.toString());
        return new ResponseUtil("200", dto.toString() + " : Added", null);
    }

}
