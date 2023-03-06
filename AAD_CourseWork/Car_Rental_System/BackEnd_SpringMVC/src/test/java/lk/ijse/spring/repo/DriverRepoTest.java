package lk.ijse.spring.repo;

import lk.ijse.spring.config.WebRootConfig;
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
class DriverRepoTest {
    @Autowired
    DriverRepo driverRepo;

    @Test
    public void deleteDriver(){
        driverRepo.deleteById("D-001");
    }

}