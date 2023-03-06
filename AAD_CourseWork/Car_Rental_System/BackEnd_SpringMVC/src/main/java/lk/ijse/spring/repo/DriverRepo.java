package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepo extends JpaRepository<Driver, String> {

    /*GET DRIVER BY ID*/
    Driver findDriverByDriverId(String driverId);

    /*GET DRIVER TABLE LAST ID*/
    @Query(value = "SELECT driverId FROM driver ORDER BY driverId DESC LIMIT 1", nativeQuery = true)
    String getLastId();

    /*FIND DRIVER TABLE IS EMPTY*/
    @Query(value = "SELECT COUNT(driverId) AS NumberOfDrivers FROM driver", nativeQuery = true)
    int getRowCount();
}
