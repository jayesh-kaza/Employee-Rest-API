package com.springboot.Employee.Rest.API.Controllers;

import com.springboot.Employee.Rest.API.Entity.Employee;
import com.springboot.Employee.Rest.API.Exceptions.EmployeeNotFoundException;
import com.springboot.Employee.Rest.API.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/list")
    public String getEmployees(Model model)
    {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees",employees);
        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showAddForm(Model model)
    {
        Employee employee =  new Employee();
        model.addAttribute("employee",employee);

        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "employee-form";

        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("employeeId")int empId,Model model)
    {
        Employee employee = employeeService.findById(empId);

        model.addAttribute("employee",employee);

        return "employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId)
    {

        employeeService.deleteById(employeeId);

        return "redirect:/employees/list";
    }

}
