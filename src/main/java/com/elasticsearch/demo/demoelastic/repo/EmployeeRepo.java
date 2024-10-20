package com.elasticsearch.demo.demoelastic.repo;

import com.elasticsearch.demo.demoelastic.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends ElasticsearchRepository<Employee, Integer> {

    List<Employee> findByName(String name);

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findBySalaryBetween(double minSalary, double maxSalary);

    @Query(value = "{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    List<Employee> findByNameMatchQuery(String name);
    @Query(value = "{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}},{\"range\": {\"salary\": {\"gte\": ?1}}}]}}")
    List<Employee> findBYMatchNameandSalaryRange(String name, double minSalary);

    @Query(value = "{\"range\": {\"salary\": {\"gte\": ?0,\"lte\": ?1}}}")
    List<Employee> findBySalaryRange(double minSalary, double maxSalary);


}
