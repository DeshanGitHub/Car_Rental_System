package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;

import java.util.ArrayList;

public interface CustomerService {
    void addCustomer(CustomerDTO dto);

    void deleteCustomer(String id);

    void updateCustomer(CustomerDTO dto);

    ArrayList<CustomerDTO> getAllCustomers();

    void isExistsCustomer(String id);

    String getLastCustomerId();

    int getCustomersCount();
}
