package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.EmployeeDTO;
import lk.ijse.spring.entity.Employee;
import lk.ijse.spring.repo.EmployeeRepo;
import lk.ijse.spring.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addEmployee(EmployeeDTO dto) {
        if (employeeRepo.existsById(dto.getId())) {
            throw new RuntimeException("Employee " + dto.getId() + " Already Exists..!!");
        }
        if (employeeRepo.existsEmployeeByUserName(dto.getUserName())) {
            throw new RuntimeException("Username Already Exists..!! Please Try Another User Name");
        }
        employeeRepo.save(mapper.map(dto, Employee.class));
    }

    @Override
    public void deleteEmployee(String id) {
        if (!employeeRepo.existsById(id)) {
            throw new RuntimeException("Employee " + id + " Not Available to Delete..!");
        }
        employeeRepo.deleteById(id);
    }

    @Override
    public void updateEmployee(EmployeeDTO dto) {
        if (!employeeRepo.existsById(dto.getId())) {
            throw new RuntimeException("Employee " + dto.getId() + " Not Available to Update..!");
        }
        if (employeeRepo.existsEmployeeByUserName(dto.getUserName())) {
            throw new RuntimeException("Username Already Exists..!! Please Try Another User Name");
        }
        employeeRepo.save(mapper.map(dto, Employee.class));
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() {
        return mapper.map(employeeRepo.findAll(), new TypeToken<ArrayList<EmployeeDTO>>() {
        }.getType());
    }

    @Override
    public EmployeeDTO getEmployeeByUserNameAndPassword(String userName, String password) {
        Employee employee = employeeRepo.findByUserNameAndPassword(userName, password);

        if(employee==null){
            throw new RuntimeException("Incorrect Password and Username!!");
        }
        return mapper.map(employee, EmployeeDTO.class);
    }
}
