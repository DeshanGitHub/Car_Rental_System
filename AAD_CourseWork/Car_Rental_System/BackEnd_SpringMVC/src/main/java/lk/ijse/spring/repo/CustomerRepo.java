package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer, String> {



    /*GET CUSTOMER TABLE LAST ID*/
    @Query(value = "SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1", nativeQuery = true)
    String getLastId();

    /*FIND CUSTOMER TABLE IS EMPTY*/
    @Query(value="SELECT COUNT(cusId) AS NumberOfCustomers FROM customer", nativeQuery=true)
    int getRowCount();

    /*GET CUSTOMER BY ID*/
    Customer findCustomerByCusId(String cusId);

}
