package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    /*DELETE CUSTOMER*/
    @DeleteMapping(params = "cusId")
    public ResponseUtil deleteCustomer(String cusId){
        customerService.deleteCustomer(cusId);
        return new ResponseUtil("200", cusId + " : Deleted", null);
    }

    /*CREATE STATIC VARIABLES TO STORE IMAGE UPLOADING PATH*/
    static String frontImageUploadingPath;
    static String backImageUploadPath;

    /*UPDATE CUSTOMER WITHOUT IMAGES*/
    @PutMapping(path = "/updateWithoutImages")
    public ResponseUtil updateCustomerWithoutImages(@RequestBody CustomerDTO dto) {
        customerService.updateCustomerWithoutImages(dto);
        return new ResponseUtil("200", "Updated", null);
    }

    /*GET CUSTOMER BY ID*/
    @GetMapping(path = "/getCus/{cusId}")
    public ResponseUtil getCustomerById(@PathVariable String cusId) {
        CustomerDTO customer = customerService.getCustomerById(cusId);
        return new ResponseUtil("200", "success", customer);
    }

    /*GET ALL CUSTOMERS*/
    @GetMapping
    public ResponseUtil getAllCustomers() {
        ArrayList<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseUtil("200", "success", allCustomers);
    }

    /*GET LAST CUSTOMER ID*/
    @GetMapping(path = "/cusId")
    public ResponseUtil getLastCustomerIdFromCustomerDbTbl() {
        String lastCustomerId;
        if (customerService.getCustomersCount() > 0) {
            lastCustomerId = customerService.getLastCustomerId();
        } else {
            lastCustomerId = "CUS-000";
        }
        return new ResponseUtil("200", "success", lastCustomerId);
    }

    @PostMapping(path = "/image/{cusId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil uploadImages(@RequestPart("frontImage") MultipartFile frontImage, @RequestPart("backImage") MultipartFile backImage, @PathVariable String cusId) {
        System.out.println("Save Image method invoked.");
        try {
            /*FIND CUSTOMER IS EXISTS BEFORE SAVING IMAGES*/
            customerService.isExistsCustomer(cusId);

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
        customerService.addCustomer(dto);
        return new ResponseUtil("200", dto.toString() + " : Added", null);
    }

}
