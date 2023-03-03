package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addCustomer(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getCusId())) {
            throw new RuntimeException("Customer " + dto.getCusId() + " Already Exists..!!");
        }
        customerRepo.save(mapper.map(dto, Customer.class));
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerRepo.existsById(id)) {
            throw new RuntimeException("Customer " + id + " Not Available to Delete..!");
        }
        customerRepo.deleteById(id);
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {
        if (!customerRepo.existsById(dto.getCusId())) {
            throw new RuntimeException("Customer " + dto.getCusId() + " Not Available to Update..!");
        }
        customerRepo.save(mapper.map(dto, Customer.class));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() {
        return mapper.map(customerRepo.findAll(), new TypeToken<ArrayList<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public void isExistsCustomer(String id) {
        if(customerRepo.existsById(id)){
            throw new RuntimeException("Customer " + id + " Already Exists..!!");
        }
    }

    @Override
    public String getLastCustomerId() {

        return customerRepo.getLastId();
    }

    @Override
    public int getCustomersCount() {
        return customerRepo.getRowCount();
    }
}
