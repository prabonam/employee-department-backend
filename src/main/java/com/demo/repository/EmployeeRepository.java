/**
 * 
 */
package com.demo.repository;

/**
 * @author PrasadBonam
 *
 */
 
import com.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}