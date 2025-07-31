/**
 * 
 */
package com.demo.service;

/**
 * @author PrasadBonam
 *
 */
import com.demo.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    boolean deleteEmployee(Long id);
}