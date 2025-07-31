package com.test.service;

import com.demo.model.Department;
import com.demo.service.DepartmentService;
import com.demo.controller.DepartmentController;
import com.demo.exception.DepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private Department sampleDepartment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleDepartment = new Department();
        sampleDepartment.setId(1L);
        sampleDepartment.setName("HR");
        sampleDepartment.setLocation("Hyderabad");
    }

    @Test
    public void testGetAllDepartments() {
        when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(sampleDepartment));

        ResponseEntity<List<Department>> response = departmentController.getAllDepartments();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    public void testGetDepartmentById_Found() {
        when(departmentService.getDepartmentById(1L)).thenReturn(sampleDepartment);

        ResponseEntity<Department> response = departmentController.getDepartmentById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("HR", response.getBody().getName());
    }

    @Test
    public void testGetDepartmentById_NotFound() {
        when(departmentService.getDepartmentById(1L)).thenReturn(null);

        assertThrows(DepartmentNotFoundException.class, () -> departmentController.getDepartmentById(1L));
    }

    @Test
    public void testCreateDepartment() {
        when(departmentService.createDepartment(sampleDepartment)).thenReturn(sampleDepartment);

        ResponseEntity<Department> response = departmentController.createDepartment(sampleDepartment);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("HR", response.getBody().getName());
    }

    @Test
    public void testUpdateDepartment_Found() {
        when(departmentService.updateDepartment(1L, sampleDepartment)).thenReturn(sampleDepartment);

        ResponseEntity<Department> response = departmentController.updateDepartment(1L, sampleDepartment);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("HR", response.getBody().getName());
    }

    @Test
    public void testUpdateDepartment_NotFound() {
        when(departmentService.updateDepartment(1L, sampleDepartment)).thenReturn(null);

        assertThrows(DepartmentNotFoundException.class, () -> departmentController.updateDepartment(1L, sampleDepartment));
    }

    @Test
    public void testDeleteDepartment_Found() {
        when(departmentService.deleteDepartment(1L)).thenReturn(true);

        ResponseEntity<Void> response = departmentController.deleteDepartment(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteDepartment_NotFound() {
        when(departmentService.deleteDepartment(1L)).thenReturn(false);

        assertThrows(DepartmentNotFoundException.class, () -> departmentController.deleteDepartment(1L));
    }
}