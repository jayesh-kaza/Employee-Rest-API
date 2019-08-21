package com.springboot.Employee.Rest.API.SpringDataJPA;

import com.springboot.Employee.Rest.API.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// to put custom mapping
@RepositoryRestResource(path="members")                  //entity type, primary key
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
// no need to write code.
}
