package com.springboot.Employee.Rest.API.Service;

import com.springboot.Employee.Rest.API.DAO.EmployeeDAO;
import com.springboot.Employee.Rest.API.Entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

    @Autowired
    EmployeeService employeeService;

    @MockBean
    EmployeeDAO employeeDAO;

    private Employee mockEmployee = new Employee("Monkey D","Dragon","revolutionary@onepice.com");

    private String exampleEmployee = "{\"firstName\":\"Monkey D\",\"lastName\":\"Dragon\",\"email\":\"revolutionary@onepice.com\"}";

    @Test
    public void findAll() {
        when(employeeDAO.findAll()).thenReturn(Stream.of(new Employee("Monkey D","Luffy","priateking@onepiece.com"),
                new Employee("Monkey D","Dragon","revolutionary@onepice.com")).collect(Collectors.toList()));
        Assert.assertEquals(2,employeeService.findAll().size());
    }

    @Test
    public void findById() {
        when(employeeDAO.findById(Mockito.anyInt())).thenReturn(mockEmployee);
        assertEquals(mockEmployee,employeeService.findById(Mockito.anyInt()));
    }

    @Test
    public void save() {
        when(employeeDAO.save(Mockito.any(Employee.class))).thenReturn(mockEmployee);
        assertEquals(mockEmployee,employeeService.save(mockEmployee));
    }

    @Test
    public void deleteById() {
        employeeService.deleteById(Mockito.anyInt());
        verify(employeeDAO,times(1)).deleteById(Mockito.anyInt());
    }
}