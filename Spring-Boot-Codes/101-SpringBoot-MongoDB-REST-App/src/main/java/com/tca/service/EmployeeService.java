package com.tca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.Employee;
import com.tca.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}