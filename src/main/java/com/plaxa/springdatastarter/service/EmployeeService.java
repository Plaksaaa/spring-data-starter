package com.plaxa.springdatastarter.service;


import com.plaxa.springdatastarter.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmployee();

    void saveEmployee(Employee employee);

    Optional<Employee> getEmployee(Integer id);

    void deleteEmployee(Integer id);

    List<Employee> findAllByFirstname(String firstname);
}
