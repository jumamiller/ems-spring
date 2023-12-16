package com.bikebuka.payroll.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long Id) {
        super("Sorry, an employee with ID "+Id+" not found");
    }
}
