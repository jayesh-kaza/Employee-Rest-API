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
    private final EntityManager entityManager;

    //setup constructor injection
    @Autowired          //automatically created by spring boot
    public EmployeeDAOImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        //get current hibernate session
        final Session session = entityManager.unwrap(Session.class);

        final List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();

        return employees;
    }

    @Override
    public Employee findById(final int id) {
        //get session
        final Session session = entityManager.unwrap(Session.class);

        final Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public Employee save(final Employee employee) {

        final Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);
        return employee;
    }

    @Override
    public void deleteById(final int id) {

        final Session session = entityManager.unwrap(Session.class);

        final Employee employee = session.get(Employee.class, id);
        session.delete(employee);

    }
}