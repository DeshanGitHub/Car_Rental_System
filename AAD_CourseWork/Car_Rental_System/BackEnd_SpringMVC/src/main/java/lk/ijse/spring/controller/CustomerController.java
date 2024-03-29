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

    public static String loggedCustomerId;

    /*GET LOGGED CUSTOMER ID*/
    @GetMapping(path = "/loggedCustomerId")
    public ResponseUtil getLoggedCustomerId(){
        return new ResponseUtil("200","success",loggedCustomerId);
    }

    /*DELETE UPLOADED IMAGE*/
    public boolean deleteUploadedImg(String frontImagePath, String backImagePath) {
        try {

            File fileFront = new File(frontImagePath);
            File fileBack = new File(backImagePath);

            if (fileFront.delete() && fileBack.delete()) {
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


    /*DELETE CUSTOMER*/
    @DeleteMapping(params = "cusId")
    public ResponseUtil deleteCustomer(String cusId) {
        CustomerDTO customer = customerService.getCustomerById(cusId);
        customerService.deleteCustomer(cusId);
        boolean isDeletedImages = deleteUploadedImg(customer.getCusFrontImgPath(), customer.getCusBackImgPath());
        if (isDeletedImages) {
            return new ResponseUtil("200", cusId + " : Deleted", null);
        } else {
            return new ResponseUtil("500", "Customer Details Are Deleted, But Images Are Not Deleted!!!", null);
        }

    }

    @GetMapping(path = "/getCustomer/{userName}/{password}")
    public ResponseUtil getCustomerBySearchingUserNameAndPassword(@PathVariable String userName, @PathVariable String password) {
        //System.out.println("Method Called : " + userName + ", " + password);
        CustomerDTO customerDTO = customerService.getCustomerByUserNameAndPassword(userName, password);

        /*STORE CUSTOMER ID FOR GET THAT CUSTOMER AFTER LOGIN TO APPLICATION*/
        loggedCustomerId = customerDTO.getCusId();

        return new ResponseUtil("200", "success", customerDTO);
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

    @PostMapping(path = "/image/{cusId}/{cusUserName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil uploadImages(@RequestPart("frontImage") MultipartFile frontImage, @RequestPart("backImage") MultipartFile backImage, @PathVariable String cusId, @PathVariable String cusUserName) {
        System.out.println("Save Image method invoked.");
        try {
            /*CHECK CUSTOMER IS EXISTS BEFORE SAVING IMAGES*/
            customerService.isExistsCustomer(cusId);

            /*CHECK CUSTOMER USERNAME IS EXISTS BEFORE SAVING IMAGES*/
            customerService.findCustomerUserNameIsExists(cusUserName);

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
