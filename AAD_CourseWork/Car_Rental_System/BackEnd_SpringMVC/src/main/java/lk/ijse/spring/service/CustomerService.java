package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.EmployeeDTO;

import java.util.ArrayList;

public interface CustomerService {
    void addCustomer(CustomerDTO dto);

    void deleteCustomer(String id);

    void updateCustomer(CustomerDTO dto);

    ArrayList<CustomerDTO> getAllCustomers();

    void isExistsCustomer(String id);

    String getLastCustomerId();

    int getCustomersCount();

    CustomerDTO getCustomerById(String cusId);

    void updateCustomerWithoutImages(CustomerDTO dto);

    CustomerDTO getCustomerByUserNameAndPassword(String userName, String password);

    void findCustomerUserNameIsExists(String userName);
}
