package com.elasticsearch.demo.demoelastic.service;

import com.elasticsearch.demo.demoelastic.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Iterable<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    Employee updateEmployee(int id, Employee employee);

    void deleteEmployee(int id);

    List<Employee> findByName(String name);

    Page<Employee> paginateEmployees(int page, int size);

    List<Employee> filterEmployeesBySalaryRange(double min, double max);


    List<Employee> searchEmployeeByNameMatch(String name);

    List<Employee> searchEmployeeByNameAndSalaryRange(String name, double minSalary);

    List<Employee> findBySalaryRange(double minSalary, double maxSalary);

}