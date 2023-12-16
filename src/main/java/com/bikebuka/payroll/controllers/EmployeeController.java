package com.bikebuka.payroll.controllers;

import com.bikebuka.payroll.exception.EmployeeNotFoundException;
import com.bikebuka.payroll.model.Employee;
import com.bikebuka.payroll.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    /**
     * Get all
     */
    @GetMapping("employees")
    public List<Employee> index() {
        return employeeRepository.findAll();
    }

    /**
     * Add new
     */
    @PostMapping("employees")
    public Employee store(@RequestBody Employee employee) {
        return this.employeeRepository.save(employee);
    }

    /**
     * show employee
     */
    @GetMapping("employees/{id}")
    public Employee show(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /**
     * Update
     */
    @PatchMapping("employees/{id}")
    public Employee update(@RequestBody Employee employee,@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(item -> {
                    item.setName(employee.getName());
                    item.setRole(employee.getRole());
                    return employeeRepository.save(item);
                })
                .orElseGet(()->{
                    employee.setId(id);
                    return employeeRepository.save(employee);
                });
    }

    /**
     * Remove
     */
    @DeleteMapping("employees/{id}")
    public void delete(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
