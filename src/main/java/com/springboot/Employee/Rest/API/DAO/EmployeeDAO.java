package com.springboot.Employee.Rest.API.DAO;

import com.springboot.Employee.Rest.API.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteById(int id);
}