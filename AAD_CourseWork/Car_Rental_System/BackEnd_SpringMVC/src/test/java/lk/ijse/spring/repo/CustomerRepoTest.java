package lk.ijse.spring.repo;

import lk.ijse.spring.config.WebRootConfig;
import lk.ijse.spring.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@WebAppConfiguration
@ContextConfiguration(classes = {WebRootConfig.class}) // for test context configurations
@ExtendWith(SpringExtension.class)
@Transactional
class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;

    @Test
    void getLastId() {
        System.out.println(customerRepo.getLastId());
    }

    @Test
    void exists() {
        int rowCount = customerRepo.getRowCount();
        System.out.println(rowCount);

    }

    @Test
    void getCustomerById() {
        Customer customer = customerRepo.findCustomerByCusId("CUS-002");
        System.out.println(customer.toString());
    }

    @Test
    void updateCustomerWithoutImage() {
        customerRepo.updateCustomerWithoutImages("Kavindu", "Deshan", "Mathugama", "0787837234", "NIC", "35q45v", "k@gmail.com", "abcd", "1234", "Confirm", "Ok", "C-001");

    }


}