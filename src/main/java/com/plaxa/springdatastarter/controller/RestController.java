package com.plaxa.springdatastarter.controller;

import com.plaxa.springdatastarter.entity.Employee;
import com.plaxa.springdatastarter.model.AuthenticationRequest;
import com.plaxa.springdatastarter.model.AuthenticationResponse;
import com.plaxa.springdatastarter.security.MyUserDetailsService;
import com.plaxa.springdatastarter.service.EmployeeService;
import com.plaxa.springdatastarter.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("error", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

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
