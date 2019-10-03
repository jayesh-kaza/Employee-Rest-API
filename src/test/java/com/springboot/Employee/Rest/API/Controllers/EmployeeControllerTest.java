package com.springboot.Employee.Rest.API.Controllers;

import com.springboot.Employee.Rest.API.Entity.Employee;
import com.springboot.Employee.Rest.API.Service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class,secure = false)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee mockEmployee = new Employee("Monkey D","Dragon","revolutionary@onepice.com");

    private String exampleEmployee = "{\"firstName\":\"Monkey D\",\"lastName\":\"Dragon\",\"email\":\"revolutionary@onepice.com\"}";

    @Test
    public void getEmployeesTest() throws Exception
    {
        Mockito.when(employeeService.findAll()).thenReturn(Stream.of(new Employee("Monkey D","Luffy","priateking@onepiece.com"),
                new Employee("Monkey D","Dragon","revolutionary@onepice.com")).collect(Collectors.toList()));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void getEmployeeByIdTest() throws Exception
    {
        Mockito.when(employeeService.findById(1)).thenReturn(new Employee("Monkey D","Dragon","revolutionary@onepice.com"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(exampleEmployee,result.getResponse().getContentAsString(),false);
    }

    @Test
    public void getEmployeeByIdErrorTest() throws Exception
    {
        Mockito.when(employeeService.findById(Mockito.anyInt())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertEquals(404,result.getResponse().getStatus());
    }

    @Test
    public void addEmployeeTest() throws Exception
    {
        Mockito.when(employeeService.save(Mockito.any(Employee.class))).thenReturn(mockEmployee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employees")
                                        .accept(MediaType.APPLICATION_JSON)
                                        .content(exampleEmployee)
                                        .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(exampleEmployee,result.getResponse().getContentAsString(),false);
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void updateEmployeeTest() throws Exception
    {
        Mockito.when(employeeService.save(Mockito.any(Employee.class))).thenReturn(mockEmployee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/employees")
                                        .accept(MediaType.APPLICATION_JSON)
                                        .content(exampleEmployee)
                                        .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
//        JSONAssert.assertEquals(exampleEmployee,result.getResponse().getContentAsString(),false);
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void deleteEmployeeTest() throws Exception
    {
        Mockito.when(employeeService.findById(Mockito.anyInt())).thenReturn(mockEmployee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/employees/0");
        MvcResult result =  mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        verify(employeeService,times(1)).deleteById(0);
    }

    @Test
    public void deleteEmployeeErrorTest() throws Exception
    {
        Mockito.when(employeeService.findById(Mockito.anyInt())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/employees/0");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertEquals(404,result.getResponse().getStatus());
    }


}