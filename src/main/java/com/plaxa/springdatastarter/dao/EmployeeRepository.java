package com.plaxa.springdatastarter.dao;

import com.plaxa.springdatastarter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByFirstname(String firstname);
}
