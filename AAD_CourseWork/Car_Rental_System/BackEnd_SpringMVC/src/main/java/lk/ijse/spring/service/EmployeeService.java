package lk.ijse.spring.service;

import lk.ijse.spring.dto.EmployeeDTO;

import java.util.ArrayList;

public interface EmployeeService {
        void addEmployee(EmployeeDTO dto);

        void deleteEmployee(String id);

        void updateEmployee(EmployeeDTO dto);

        ArrayList<EmployeeDTO> getAllEmployees();
}
