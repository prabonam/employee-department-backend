/**
 * 
 */
package com.demo.service.impl;

/**
 * @author PrasadBonam
 *
 */

import com.demo.model.Department;
import com.demo.repository.DepartmentRepository;
import com.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepo.findById(id).orElseThrow(null);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department existing = departmentRepo.findById(id).orElseThrow(null);
        existing.setName(department.getName());
        existing.setLocation(department.getLocation());
        return departmentRepo.save(existing);
    }

    @Override
    public boolean deleteDepartment(Long id) {
        if (!departmentRepo.existsById(id)) {
            return false; // or throw new DepartmentNotFoundException("Department not found with ID: " + id);
        }

        departmentRepo.deleteById(id);
        return true;
    }

}