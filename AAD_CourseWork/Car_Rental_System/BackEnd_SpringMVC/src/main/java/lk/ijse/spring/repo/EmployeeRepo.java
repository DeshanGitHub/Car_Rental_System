package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, String> {

    boolean existsEmployeeByUserName(String userName);

    boolean existsEmployeeByUserNameAndPassword(String userName, String password);

    Employee findByUserNameAndPassword(String userName, String password);
}
