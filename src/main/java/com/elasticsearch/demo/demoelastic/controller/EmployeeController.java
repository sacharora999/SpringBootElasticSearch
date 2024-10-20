package com.elasticsearch.demo.demoelastic.controller;

import com.elasticsearch.demo.demoelastic.model.Employee;
import com.elasticsearch.demo.demoelastic.service.EmployeeService;
import org.apache.http.params.HttpProtocolParamBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.createEmployee(employee);
        return  new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Employee>> getAllEmployees(){
        Iterable<Employee> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Employee employeeById = employeeService.getEmployeeById(id);

        if (employeeById !=null){
            return new ResponseEntity<>(employeeById,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee updateEmployee = employeeService.updateEmployee(id, employee);

        if (updateEmployee != null){
            return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteeEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployeeByName(@RequestParam String name){
        List<Employee> employees = employeeService.findByName(name);
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    //50 records

    //page 0,size 10

    //0 to 10

    //page 1, size 10
    //10 to 20

    //page 2, size 10
    //20 to 30
    @GetMapping("page")
    public ResponseEntity<Page<Employee>> panginateEmployees(@RequestParam int page, @RequestParam int size){
        Page<Employee> employees = employeeService.paginateEmployees(page, size);

        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @GetMapping("filter")
    public ResponseEntity<List<Employee>> filterEmployeesBySalaryRange(@RequestParam double min, @RequestParam double max){
        List<Employee> employees = employeeService.filterEmployeesBySalaryRange(min, max);
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @GetMapping("/search/match")
    public ResponseEntity<List<Employee>> searchByNameMatchQuery(@RequestParam String name){
        List<Employee> employees = employeeService.searchEmployeeByNameMatch(name);

        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @GetMapping("/search/match-range")
    public ResponseEntity<List<Employee>> searchEmployeeByNameAndSalaryRange(@RequestParam String name, @RequestParam double minSalary){
        List<Employee> employees = employeeService.searchEmployeeByNameAndSalaryRange(name,minSalary);

        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @GetMapping("search/range")
    public ResponseEntity<List<Employee>> findBySalaryRange(@RequestParam double minSalary, @RequestParam double maxSalary){
        List<Employee> employees = employeeService.findBySalaryRange(minSalary,maxSalary);

        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

}
