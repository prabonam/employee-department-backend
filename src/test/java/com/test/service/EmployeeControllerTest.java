/**
 * 
 */
package com.test.service;

import com.demo.controller.EmployeeController;

/**
 * @author PrasadBonam
 *
 */ 

import com.demo.exception.EmployeeNotFoundException;
import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private Employee employee;
    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department();
        department.setId(1L);
        department.setName("HR");
        department.setLocation("Hyderabad");

        employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        //employee.setSalary(50000.0);
        employee.setDepartment(department);
    }

    @Test
    void testGetAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee));

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getName());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeService.getEmployeeById(1L)).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> employeeController.getEmployeeById(1L));
    }

    @Test
    void testCreateEmployee() {
        when(employeeService.createEmployee(employee)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.createEmployee(employee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John", response.getBody().getName());
        verify(employeeService, times(1)).createEmployee(employee);
    }

    @Test
    void testUpdateEmployee() {
        when(employeeService.updateEmployee(1L, employee)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(1L, employee);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getName());
        verify(employeeService, times(1)).updateEmployee(1L, employee);
    }

    @Test
    void testUpdateEmployee_NotFound() {
        when(employeeService.updateEmployee(1L, employee)).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> employeeController.updateEmployee(1L, employee));
    }

    @Test
    void testDeleteEmployee() {
        when(employeeService.deleteEmployee(1L)).thenReturn(true);

        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }

    @Test
    void testDeleteEmployee_NotFound() {
        when(employeeService.deleteEmployee(1L)).thenReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeController.deleteEmployee(1L));
    }
}
