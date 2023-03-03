package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer, String> {

    /*GET CUSTOMER TABLE LAST ID*/
    @Query(value = "SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1", nativeQuery = true)
    String getLastId();

}
