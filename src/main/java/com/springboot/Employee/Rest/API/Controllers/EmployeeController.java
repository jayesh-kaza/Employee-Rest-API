package com.springboot.Employee.Rest.API.Controllers;

import com.springboot.Employee.Rest.API.Entity.Employee;
import com.springboot.Employee.Rest.API.Exceptions.EmployeeNotFoundException;
import com.springboot.Employee.Rest.API.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //endpoint for returning all employees
    @GetMapping("/employees")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    //endpoint for GET/employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Employee getEmployee(@PathVariable final int employeeId) {
        final Employee employee = employeeService.findById(employeeId);
        if (employee == null)
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        return employee;
    }

    //endpoint for POST/employees
    @PostMapping("/employees")
    @CrossOrigin(origins = "http://localhost:3000")
    public Employee addEmployee(@RequestBody final Employee employee) {
        //incase the id is passed in json, set it to '0' so that hibernates 'inserts' it
        //else hibernate will update the table
        employee.setId(0);
        return employeeService.save(employee);
    }

    //endpoint for PUT/employees
    @PutMapping("/employees")
    @CrossOrigin(origins = "http://localhost:3000")
    public Employee updateEmployee(@RequestBody final Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    //endpoint for DELETE/employees
    @DeleteMapping("/employees/{employeeId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String deleteEmployee(@PathVariable final int employeeId) {
        final Employee employee = employeeService.findById(employeeId);

        if (employee == null)
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        employeeService.deleteById(employeeId);
        return "Deleted employee with id - " + employeeId;
    }
}
