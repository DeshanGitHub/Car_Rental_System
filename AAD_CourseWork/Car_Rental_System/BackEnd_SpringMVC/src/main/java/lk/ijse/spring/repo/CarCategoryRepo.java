package lk.ijse.spring.repo;

import lk.ijse.spring.entity.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarCategoryRepo extends JpaRepository<CarCategory, String> {

    /*GET CAR CATEGORY USING ID*/
    CarCategory findByCarCategoryId(String id);

    /*GET CAR CATEGORY TABLE LAST ID*/
    @Query(value = "SELECT carCategoryId FROM carcategory ORDER BY carCategoryId DESC LIMIT 1", nativeQuery = true)
    String getCarCategoryLastId();

    /*FIND CAR CATEGORY TABLE IS EMPTY*/
    @Query(value = "SELECT COUNT(carCategoryId) AS NumberOfCarCategories FROM carcategory", nativeQuery = true)
    int getCarCategoryRowCount();
}
