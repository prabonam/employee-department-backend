/**
 * 
 */
package com.demo.controller;

import java.util.List;
//http://localhost:8080//api/departments/getAllDepartments/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.DepartmentNotFoundException;
/**
 * @author PrasadBonam
 *
 */
import com.demo.model.Department;
import com.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getDepartments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        if (department == null) {
            throw new DepartmentNotFoundException("Department not found with ID: " + id);
        }
        return ResponseEntity.ok(department);
    }

    @PostMapping("/addDepartment")
    public ResponseEntity<Department> createDepartment(  @RequestBody Department department) {
        Department created = departmentService.createDepartment(department);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
                                                         @RequestBody Department department) {
        Department updated = departmentService.updateDepartment(id, department);
        if (updated == null) {
            throw new DepartmentNotFoundException("Department not found with ID: " + id);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        boolean deleted = departmentService.deleteDepartment(id);
        if (!deleted) {
            throw new DepartmentNotFoundException("Department not found with ID: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}


/*****
 * 
/**
 * 
 * 1. Get All Departments

Method: GET
URL: http://localhost:8080/api/departments/getDepartments

2. Get Department by ID

Method: GET
URL: http://localhost:8080/api/departments/{id}
Example: http://localhost:8080/api/departments/1

3. Add Department

Method: POST
URL: http://localhost:8080/api/departments/addDepartment
Headers:

Content-Type: application/json

Body (raw JSON):

{
  "name": "Engineering",
  "location": "New York"
}

4. Update Department

Method: PUT
URL: http://localhost:8080/api/departments/{id}
Example: http://localhost:8080/api/departments/1
Headers:

Content-Type: application/json

Body (raw JSON):

{
  "name": "Engineering",
  "location": "San Francisco"
}

5. Delete Department

Method: DELETE
URL: http://localhost:8080/api/departments/{id}
Example: http://localhost:8080/api/departments/1

/*
 */

/* ============================================= */
/**
Department APIs
1. Get All Departments
 
GET /api/departments/getDepartments
Request: None
Response:

 
[
 {
   "id": 1,
   "name": "HR",
   "location": "Hyderabad",
   "employees": []
 }
]
2. Get Department by ID
 
GET /api/departments/{id}
Example:

 
GET /api/departments/1
3. Add Department
 
POST /api/departments/addDepartment
Body (raw JSON):

 
{
 "name": "Engineering",
 "location": "Bangalore"
}
4. Update Department
 
PUT /api/departments/{id}
Example:

 
PUT /api/departments/1
Body:

 
{
 "name": "Engineering Updated",
 "location": "Chennai"
}
5. Delete Department
 
DELETE /api/departments/{id}
Example:

 
DELETE /api/departments/1

**/
