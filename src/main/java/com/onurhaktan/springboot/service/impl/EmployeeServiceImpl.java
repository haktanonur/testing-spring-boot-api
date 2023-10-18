package com.onurhaktan.springboot.service.impl;

import com.onurhaktan.springboot.exception.ResourceNotFoundException;
import com.onurhaktan.springboot.model.Employee;
import com.onurhaktan.springboot.repository.EmployeeRepository;
import com.onurhaktan.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email: " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }
}