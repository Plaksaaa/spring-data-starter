package com.plaxa.springdatastarter.service;

import com.plaxa.springdatastarter.dao.EmployeeRepository;
import com.plaxa.springdatastarter.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployee(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByFirstname(String firstname) {
        return employeeRepository.findAllByFirstname(firstname);
    }
}
