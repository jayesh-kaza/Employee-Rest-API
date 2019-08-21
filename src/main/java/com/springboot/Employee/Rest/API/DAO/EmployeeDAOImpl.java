package com.springboot.Employee.Rest.API.DAO;

import com.springboot.Employee.Rest.API.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

//JPA Implementation
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    //setup constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        //create a query
        Query theQuery = entityManager.createQuery("from Employee");

        //execute query
        List<Employee> employees = theQuery.getResultList();

        //return results
        return employees;
    }

    @Override
    public Employee findById(int id) {
       //get Employee
        Employee employee = entityManager.find(Employee.class,id);

        //return employee
        return employee;
    }

    @Override
    public void save(Employee employee) {

       //save or update the employee
        Employee dbemployee = entityManager.merge(employee);

        //update with id from db .. so we can get generated id for save/insert
        employee.setId(dbemployee.getId());
    }

    @Override
    public void deleteById(int id) {

       //delete object with primary key
        Query query = entityManager.createQuery("delete from Employee where id=:employeeId");

        query.setParameter("employeeId",id);

        query.executeUpdate();



    }
}