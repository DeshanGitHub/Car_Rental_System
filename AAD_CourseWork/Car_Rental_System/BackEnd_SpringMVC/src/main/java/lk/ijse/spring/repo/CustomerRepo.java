package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer, String> {

    Customer findByCusUserNameAndCusPassword(String userName, String password);

    /*FIND IS EXISTS USER NAME*/
    boolean existsCustomersByCusUserName(String userName);

    /*UPDATE CUSTOMER WITHOUT IMAGES*/
    @Modifying
    @Query(value = "UPDATE customer SET fName=?1, lName=?2, cusAddress=?3, cusContactNum=?4, cusImgDescription=?5, cusNicOrDlNum=?6, cusEmail=?7, cusUserName=?8, cusPassword=?9, cusStatus=?10, cusStatusReason=?11 WHERE cusId=?12", nativeQuery = true)
    void updateCustomerWithoutImages(String fName, String lName, String address, String contactNum, String imageDescription, String nicOrDlNum, String email, String userName, String password, String status, String statusReason, String cusId);

    /*GET CUSTOMER TABLE LAST ID*/
    @Query(value = "SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1", nativeQuery = true)
    String getLastId();

    /*FIND CUSTOMER TABLE IS EMPTY*/
    @Query(value = "SELECT COUNT(cusId) AS NumberOfCustomers FROM customer", nativeQuery = true)
    int getRowCount();

    /*GET CUSTOMER BY ID*/
    Customer findCustomerByCusId(String cusId);

}
