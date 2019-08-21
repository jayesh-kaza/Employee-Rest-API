package com.springboot.Employee.Rest.API.DAO;

import com.springboot.Employee.Rest.API.Entity.Employee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    //same as sessionFactory
    private EntityManager entityManager;

    //setup constructor injection
    @Autowired          //automatically created by spring boot
    public EmployeeDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        //get current hibernate session
        Session session = entityManager.unwrap(Session.class);

        List<Employee> employees = session.createQuery("from Employee",Employee.class).getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
       //get session
        Session session = entityManager.unwrap(Session.class);

        Employee employee = session.get(Employee.class,id);

        return employee;
    }

    @Override
    public void save(Employee employee) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int id) {

        Session session = entityManager.unwrap(Session.class);

        Employee employee = session.get(Employee.class,id);
        session.delete(employee);

    }
}