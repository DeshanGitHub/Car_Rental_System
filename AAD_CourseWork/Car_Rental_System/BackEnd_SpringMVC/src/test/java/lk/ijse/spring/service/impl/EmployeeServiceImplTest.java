package lk.ijse.spring.service.impl;

import lk.ijse.spring.config.WebRootConfig;
import lk.ijse.spring.dto.EmployeeDTO;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ContextConfiguration(classes = {WebRootConfig.class}) // for test context configurations
@ExtendWith(SpringExtension.class)
@Transactional
class EmployeeServiceImplTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    public void getEmployeeUsingUsernameAndPassword(){
        EmployeeDTO abcd = employeeService.getEmployeeByUserNameAndPassword("admin", "1234");

        System.out.println(abcd.toString());
    }
}