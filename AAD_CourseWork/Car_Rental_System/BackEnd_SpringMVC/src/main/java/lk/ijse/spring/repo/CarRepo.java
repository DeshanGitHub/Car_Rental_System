package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface CarRepo extends JpaRepository<Car, String> {


    /*UPDATE CAR WITHOUT IMAGES*/
    @Modifying
    @Query(value = "UPDATE car SET availability=?1, color=?2, mileage=?3, mileageLastServiced=?4, passengersCount=?5, registrationNum=?6, carCategory=?7 WHERE carId=?8", nativeQuery = true)
    void updateCarWithoutImages(boolean availability, String color, BigDecimal mileage, BigDecimal mileageLastServiced, int passengersCount, String registrationNum, String carCategory, String carId);

    /*GET CAR TABLE LAST ID*/
    @Query(value = "SELECT carId FROM car ORDER BY carId DESC LIMIT 1", nativeQuery = true)
    String getCarLastId();

    /*FIND CAR TABLE IS EMPTY*/
    @Query(value = "SELECT COUNT(carId) AS NumberOfCars FROM car", nativeQuery = true)
    int getCarRowCount();

}
