package com.elasticsearch.demo.demoelastic.service;

import com.elasticsearch.demo.demoelastic.model.Employee;
import com.elasticsearch.demo.demoelastic.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepository;
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }else{
            return null;
        }
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()){
            employee.setId(id);
            return employeeRepository.save(employee);
        }else {
            return null;
        }
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Page<Employee> paginateEmployees(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> filterEmployeesBySalaryRange(double min, double max) {
        return employeeRepository.findBySalaryBetween(min,max);
    }


    @Override
    public List<Employee> searchEmployeeByNameMatch(String name) {
        return employeeRepository.findByNameMatchQuery(name);
    }

    @Override
    public List<Employee> searchEmployeeByNameAndSalaryRange(String name, double minSalary) {
        return employeeRepository.findBYMatchNameandSalaryRange(name,minSalary);
    }

    @Override
    public List<Employee> findBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepository.findBySalaryRange(minSalary,maxSalary);
    }

}