package com.springboot.Employee.Rest.API.SpringDataJPA;

import com.springboot.Employee.Rest.API.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//entity type, primary key
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
// no need to write code.

    //automatically created
    public List<Employee> findAllByOrderByFirstNameAsc();



}
