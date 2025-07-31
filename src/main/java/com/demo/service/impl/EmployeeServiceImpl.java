/**
 * 
 */
package com.demo.service.impl;

/**
 * @author PrasadBonam
 *
 */
import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.repository.DepartmentRepository;
import com.demo.repository.EmployeeRepository;
import com.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department department = departmentRepo.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + employee.getDepartment().getId()));
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null); // Or throw an error if department is mandatory
        }

        return employeeRepo.save(employee);
    }


    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = employeeRepo.findById(id).orElseThrow(null);
        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDesignation(employee.getDesignation());
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department dept = departmentRepo.findById(employee.getDepartment().getId()).orElseThrow(null);
            existing.setDepartment(dept);
        }
        return employeeRepo.save(existing);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (!employeeRepo.existsById(id)) {
            return false; // Or throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        employeeRepo.deleteById(id);
        return true;
    }

}