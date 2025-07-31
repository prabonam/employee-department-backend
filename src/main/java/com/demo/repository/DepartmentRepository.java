/**
 * 
 */
package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author PrasadBonam
 *
 */
import com.demo.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}