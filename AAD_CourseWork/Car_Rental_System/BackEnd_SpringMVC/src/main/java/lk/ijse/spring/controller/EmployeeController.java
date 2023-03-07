package lk.ijse.spring.controller;

import lk.ijse.spring.dto.EmployeeDTO;
import lk.ijse.spring.service.EmployeeService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseUtil getAllEmployees() {
        ArrayList<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        return new ResponseUtil("200", "", allEmployees);
    }

    @DeleteMapping(params = "id")
    public ResponseUtil deleteEmployee(String id) {
        employeeService.deleteEmployee(id);
        return new ResponseUtil("200", id + " : Deleted", null);
    }

    @PutMapping
    public ResponseUtil updateEmployee(@RequestBody EmployeeDTO dto) {
        employeeService.updateEmployee(dto);
        return new ResponseUtil("200", dto.toString() + " : Updated", null);
    }

    @PostMapping
    public ResponseUtil saveEmployee(@RequestBody EmployeeDTO dto) {
        //System.out.println("Employee is : " + dto.toString());
        employeeService.addEmployee(dto);
        return new ResponseUtil("200", dto.toString() + " : Added", null);
    }

}
