/**
 * 
 */
package com.demo.service;

/**
 * @author PrasadBonam
 *
 */

import com.demo.model.Department;
import java.util.*;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    Department createDepartment(Department department);
    Department updateDepartment(Long id, Department department);
    boolean deleteDepartment(Long id);
}