package com.plaxa.springdatastarter.controller;

import com.plaxa.springdatastarter.entity.Employee;
import com.plaxa.springdatastarter.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showEmployees() {
        return employeeService.findAllEmployee();
    }

    /*@GetMapping("/employees/{id}")
    public Employee showEmployee(@PathVariable Integer id) {
        return Optional.ofNullable(employeeService.getEmployee(id))
                .orElseThrow(() -> new NoSuchEmployeeException(
                        "There is no employee with id = %d in database"
                                .formatted(id)));
    }*/

    @GetMapping("/employees/{id}")
    public Optional<Employee> showEmployee(@PathVariable Integer id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    /*@DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        Optional.ofNullable(employeeService.getEmployee(id))
                .orElseThrow(NoSuchEmployeeException::new);

        employeeService.deleteEmployee(id);
        return "Employee with id = %d was deleted".formatted(id);
    }*/

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.getEmployee(id);

        employeeService.deleteEmployee(id);
        return "Employee with id = %d was deleted".formatted(id);
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> showAllEmployeesByFirstname(@PathVariable String name) {
        return employeeService.findAllByFirstname(name);
    }
}
